/*
 *
 *  * | Licensed 未经许可不能去掉「OPENIITA」相关版权
 *  * +----------------------------------------------------------------------
 *  * | Author: xw2sy@163.com
 *  * +----------------------------------------------------------------------
 *
 *  Copyright [2024] [OPENIITA]
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * /
 */
package cc.iotkit.vertx;

import cc.iotkit.mq.ConsumerHandler;
import cc.iotkit.mq.MqConsumer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class VertxMqConsumer<T> implements MqConsumer<T> {

    private final MqConsumerVerticle<T> consumerVerticle;

    private final CountDownLatch countDownLatch = new CountDownLatch(4);

    @SneakyThrows
    public VertxMqConsumer(Class<T> cls) {
        consumerVerticle = new MqConsumerVerticle<>(cls);
        for (int i = 0; i < 4; i++) {
            VertxManager.getVertx().deployVerticle(consumerVerticle,new DeploymentOptions().setWorker(true), stringAsyncResult -> countDownLatch.countDown());
        }
        //等待初始化穿完成
        countDownLatch.await();
    }

    @Override
    public void consume(String topic, ConsumerHandler<T> handler) {
        consumerVerticle.consume(topic, handler);
    }

    public static class MqConsumerVerticle<T> extends AbstractVerticle {

        private final Class<T> cls;
        private EventBus eventBus;

        public MqConsumerVerticle(Class<T> cls) {
            this.cls = cls;
        }

        @Override
        public void start() {
            eventBus = vertx.eventBus();
            eventBus.registerCodec(new BeanCodec<>(cls));
        }

        public void consume(String topic, ConsumerHandler<T> handler) {
            eventBus.consumer(topic, (Handler<Message<T>>) msg -> handler.handler(msg.body()));
        }
    }

}

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

import cc.iotkit.mq.MqProducer;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.DeliveryOptions;
import io.vertx.core.eventbus.EventBus;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class VertxMqProducer<T> implements MqProducer<T> {

    private final MqProducerVerticle<T> producerVerticle;

    private final CountDownLatch countDownLatch = new CountDownLatch(1);

    @SneakyThrows
    public VertxMqProducer(Class<T> cls) {
        producerVerticle = new MqProducerVerticle<>(cls);
        VertxManager.getVertx().deployVerticle(producerVerticle, stringAsyncResult -> countDownLatch.countDown());
        //等待初始化完成
        countDownLatch.await();
    }

    @Override
    public void publish(String topic, T msg) {
        producerVerticle.publish(topic, msg);
    }

    public static class MqProducerVerticle<T> extends AbstractVerticle {

        private final Class<T> cls;
        private EventBus eventBus;

        public MqProducerVerticle(Class<T> cls) {
            this.cls = cls;
        }

        @Override
        public void start() {
            eventBus = vertx.eventBus();
            eventBus.registerCodec(new BeanCodec<>(cls));
        }

        public void publish(String topic, T msg) {
            eventBus.publish(topic, msg, new DeliveryOptions().setCodecName(cls.getSimpleName()));
        }
    }
}

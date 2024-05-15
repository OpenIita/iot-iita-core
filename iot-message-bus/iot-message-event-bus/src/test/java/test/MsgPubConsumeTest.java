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

package test;

import cc.iotkit.mq.ConsumerHandler;
import cc.iotkit.vertx.VertxMqConsumer;
import cc.iotkit.vertx.VertxMqProducer;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

public class MsgPubConsumeTest {


    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        VertxMqConsumer.MqConsumerVerticle<Bean1> consumerVerticle = new VertxMqConsumer.MqConsumerVerticle<>(Bean1.class);
        vertx.deployVerticle(consumerVerticle, new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {
                consumerVerticle.consume("aaa", new ConsumerHandler<Bean1>() {
                    @Override
                    public void handler(Bean1 msg) {
                        System.out.println("c1:" + msg.getName());
                    }
                });
                consumerVerticle.consume("aaa", new ConsumerHandler<Bean1>() {
                    @Override
                    public void handler(Bean1 msg) {
                        System.out.println("c2:" + msg.getName());
                    }
                });
            }
        });


        VertxMqProducer.MqProducerVerticle<Bean1> producerVerticle = new VertxMqProducer.MqProducerVerticle<>(Bean1.class);
        vertx.deployVerticle(producerVerticle, new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {
                producerVerticle.publish("aaa", new Bean1("test", 1));
                System.out.println("publish");
            }
        });
    }

    public static class Bean1 {
        private String name;
        private int age;

        public Bean1() {
        }

        public Bean1(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

}

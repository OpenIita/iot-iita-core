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

package cc.iotkit.rocketmq;

import cc.iotkit.common.utils.JsonUtils;
import cc.iotkit.mq.ConsumerHandler;
import cc.iotkit.mq.MqConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;

@Slf4j
public class RocketMqConsumer<T> implements MqConsumer<T> {

    private String nameServer;

    private final Class<T> msgType;

    public RocketMqConsumer(String nameServer, Class<T> cls) {
        this.nameServer = nameServer;
        this.msgType = cls;
    }

    @Override
    public void consume(String topic, ConsumerHandler<T> handler) {
        try {
            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(handler.getClass()
                    .getName().replace(".", ""));
            consumer.setNamesrvAddr(nameServer);
            consumer.subscribe(topic, "*");
            consumer.registerMessageListener((MessageListenerConcurrently) (messages, context) -> {
                for (MessageExt message : messages) {
                    T msg = JsonUtils.parseObject(new String(message.getBody(), StandardCharsets.UTF_8), msgType);
                    handler.handler(msg);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
            consumer.start();
        } catch (Throwable e) {
            log.error("consume error", e);
        }
    }

}

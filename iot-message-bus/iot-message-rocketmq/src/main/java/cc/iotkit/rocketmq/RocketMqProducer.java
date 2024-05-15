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

import cc.iotkit.common.enums.ErrCode;
import cc.iotkit.common.exception.BizException;
import cc.iotkit.common.utils.JsonUtils;
import cc.iotkit.mq.MqProducer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

public class RocketMqProducer<T> implements MqProducer<T> {

    private final DefaultMQProducer producer;

    public RocketMqProducer(String nameServer, String group) {
        try {
            producer = new DefaultMQProducer(group);
            producer.setNamesrvAddr(nameServer);
            producer.start();
        } catch (Throwable e) {
            throw new BizException(ErrCode.INIT_PRODUCER_ERROR, e);
        }
    }

    @Override
    public void publish(String topic, T msg) {
        try {
            producer.send(new Message(topic,
                    JsonUtils.toJsonString(msg).getBytes(StandardCharsets.UTF_8)));
        } catch (Throwable e) {
            throw new BizException(ErrCode.SEND_MSG_ERROR, e);
        }
    }

}

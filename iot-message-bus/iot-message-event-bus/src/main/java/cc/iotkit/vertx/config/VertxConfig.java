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

package cc.iotkit.vertx.config;

import cc.iotkit.common.thing.ThingModelMessage;
import cc.iotkit.mq.MqConsumer;
import cc.iotkit.mq.MqProducer;
import cc.iotkit.vertx.VertxMqConsumer;
import cc.iotkit.vertx.VertxMqProducer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VertxConfig {

    @ConditionalOnMissingBean
    @Bean
    public MqProducer<ThingModelMessage> getThingModelMessageProducer() {
        return new VertxMqProducer<>(ThingModelMessage.class);
    }

    @ConditionalOnMissingBean
    @Bean
    public MqConsumer<ThingModelMessage> getThingModelMessageConsumer() {
        return new VertxMqConsumer<>(ThingModelMessage.class);
    }

}

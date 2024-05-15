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

import cc.iotkit.common.utils.JsonUtils;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.eventbus.MessageCodec;
import io.vertx.core.json.Json;

public class BeanCodec<T> implements MessageCodec<T, T> {

    private final Class<T> beanType;

    public BeanCodec(Class<T> cls) {
        beanType = cls;
    }

    @Override
    public void encodeToWire(Buffer buffer, T o) {
        String json = Json.encode(o);
        Buffer encoded = Buffer.buffer(json);
        buffer.appendInt(encoded.length());
        buffer.appendBuffer(encoded);
    }

    @Override
    public T decodeFromWire(int pos, Buffer buffer) {
        int length = buffer.getInt(pos);
        pos += 4;
        return Json.decodeValue(buffer.slice(pos, pos + length), beanType);
    }

    @Override
    public T transform(T o) {
        return JsonUtils.parseObject(JsonUtils.toJsonString(o), beanType);
    }

    @Override
    public String name() {
        return beanType.getSimpleName();
    }

    @Override
    public byte systemCodecID() {
        return -1;
    }
}

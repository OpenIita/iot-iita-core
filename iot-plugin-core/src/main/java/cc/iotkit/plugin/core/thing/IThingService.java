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

package cc.iotkit.plugin.core.thing;

import cc.iotkit.plugin.core.thing.actions.ActionResult;
import cc.iotkit.plugin.core.thing.actions.IDeviceAction;
import cc.iotkit.plugin.core.thing.model.ThingDevice;
import cc.iotkit.plugin.core.thing.model.ThingProduct;

import java.util.Map;

/**
 * 设备服务接口
 *
 * @author sjg
 */
public interface IThingService {

    /**
     * 提交设备行为
     *
     * @param action IDeviceAction
     * @return result
     */
    ActionResult post(String pluginId, IDeviceAction action);

    /**
     * 获取产品信息
     *
     * @param pk 产品key
     * @return Product
     */
    ThingProduct getProduct(String pk);

    /**
     * 获取设备信息
     *
     * @param deviceName 设备dn
     * @return DeviceInfo
     */
    ThingDevice getDevice(String deviceName);

    /**
     * 获取设备当前属性数据
     *
     * @param deviceName 设备dn
     * @return 当前属性
     */
    Map<String, ?> getProperty(String deviceName);

}

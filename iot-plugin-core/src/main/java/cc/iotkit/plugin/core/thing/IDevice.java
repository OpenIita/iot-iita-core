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
import cc.iotkit.plugin.core.thing.actions.down.DeviceConfig;
import cc.iotkit.plugin.core.thing.actions.down.PropertyGet;
import cc.iotkit.plugin.core.thing.actions.down.PropertySet;
import cc.iotkit.plugin.core.thing.actions.down.ServiceInvoke;


/**
 * 设备接口
 *
 * @author sjg
 */
public interface IDevice {

    /**
     * 执行设备配置动作
     *
     * @param action 动作
     * @return result
     */
    ActionResult config(DeviceConfig action);

    /**
     * 执行设备属性获取动作
     *
     * @param action 动作
     * @return result
     */
    ActionResult propertyGet(PropertyGet action);

    /**
     * 执行设备属性设置动作
     *
     * @param action 动作
     * @return result
     */
    ActionResult propertySet(PropertySet action);

    /**
     * 执行设备服务调用动作
     *
     * @param action 动作
     * @return result
     */
    ActionResult serviceInvoke(ServiceInvoke action);

}

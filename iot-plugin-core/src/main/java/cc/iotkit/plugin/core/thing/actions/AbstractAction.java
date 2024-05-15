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

package cc.iotkit.plugin.core.thing.actions;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author sjg
 */
@Data
@SuperBuilder
@ToString(callSuper = true)
public abstract class AbstractAction implements IDeviceAction {

    protected String id;

    protected ActionType type;

    protected String productKey;

    protected String deviceName;

    protected Long time;

    public AbstractAction() {
    }

    public AbstractAction(ActionType type) {
        this.type = type;
    }
}

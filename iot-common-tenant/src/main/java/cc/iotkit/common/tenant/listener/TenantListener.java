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

package cc.iotkit.common.tenant.listener;


import cc.iotkit.common.satoken.utils.LoginHelper;
import cc.iotkit.common.tenant.dao.TenantAware;
import cc.iotkit.common.tenant.helper.TenantHelper;
import cc.iotkit.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * 类描述...
 *
 * @author Tiger Chen
 * created on 2023/7/14 20:50
 */

@Slf4j
public class TenantListener {

    @PreUpdate
    @PreRemove
    @PrePersist
    public void setTenant(TenantAware entity) {
        String tenantId = LoginHelper.getTenantId();
        String dynamic = TenantHelper.getDynamic();
        if (StringUtils.isNotBlank(dynamic)) {
            tenantId = dynamic;
        }
        if (!"000000".equals(tenantId) && tenantId != null) {
            entity.setTenantId(tenantId);
        }
    }
}

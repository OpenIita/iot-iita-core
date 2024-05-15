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
package cc.iotkit.common.satoken.utils;

import cc.iotkit.common.constant.Constants;
import cc.iotkit.common.utils.CodecUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.apache.commons.lang3.RandomUtils;

import java.util.List;

public class AuthUtil {

    public static String getUserId() {
        return String.valueOf(LoginHelper.getUserId());
    }

    public static List<String> getUserRoles() {
        return StpUtil.getRoleList();
    }

    public static boolean isAdmin() {
        return LoginHelper.isSuperAdmin();
    }

    public static boolean isClientUser() {
        return AuthUtil.getUserRoles().contains(Constants.ROLE_CLIENT);
    }

    public static boolean hasWriteRole() {
        return AuthUtil.getUserRoles().contains(Constants.ROLE_WRITE);
    }

    public static String enCryptPwd(String pwd) throws Exception {
        return CodecUtil.aesEncrypt(CodecUtil.md5Str(pwd) + ":"
                + RandomUtils.nextInt(1000, 9999), Constants.ACCOUNT_SECRET);
    }

    public static boolean checkPwd(String pwd, String secret) throws Exception {
        String code = CodecUtil.aesDecrypt(secret, Constants.ACCOUNT_SECRET);
        String[] arr = code.split(":");
        return arr.length > 0 && CodecUtil.md5Str(pwd).equals(arr[0]);
    }
}

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

package cc.iotkit.common.web.core;


import cc.iotkit.common.exception.ViewException;
import cc.iotkit.common.utils.StringUtils;

/**
 * web层通用数据处理
 *
 * @author Lion Li
 */
public class BaseController {

    public static void fail() {
        throw new ViewException("操作失败");
    }

    public static void fail(String msg) {
        throw new ViewException(ViewException.CODE_FAILED, msg);
    }

    public static <T> void fail(T data) {
        throw new ViewException(ViewException.CODE_FAILED, "操作失败", data);
    }

    public static <T> void fail(String msg, T data) {
        throw new ViewException(ViewException.CODE_FAILED, msg, data);
    }

    /**
     * 返回警告消息
     *
     * @param msg 返回内容
     */
    public static <T> void warn(String msg) {
        throw new ViewException(ViewException.CODE_WARN, msg);
    }

    /**
     * 返回警告消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     */
    public static <T> void warn(String msg, T data) {
        throw new ViewException(ViewException.CODE_WARN, msg, data);
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

}

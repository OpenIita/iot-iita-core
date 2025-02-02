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

package com.gitee.starblues.bootstrap.annotation;


import java.lang.annotation.*;

/**
 * 注入类型
 *
 * @author starBlues
 * @since 3.0.0
 * @version 3.0.3
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutowiredType {

    /**
     * 插件Bean注入类型
     * @return Type
     */
    Type value() default Type.PLUGIN;


    enum Type{
        /**
         * Bean 注入类型: 仅插件(默认)
         */
        PLUGIN,

        /**
         *  Bean 注入类型: 仅主程序
         */
        MAIN,

        /**
         * Bean 注入类型: 先插件后主程序
         */
        PLUGIN_MAIN,

        /**
         * Bean 注入类型: 先主程序后插件
         */
        MAIN_PLUGIN
    }


}

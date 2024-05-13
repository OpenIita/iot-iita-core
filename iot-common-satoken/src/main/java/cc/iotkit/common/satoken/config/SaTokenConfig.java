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

package cc.iotkit.common.satoken.config;

import cc.iotkit.common.satoken.core.dao.PlusSaTokenDao;
import cc.iotkit.common.satoken.core.service.SaPermissionImpl;
import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * sa-token 配置
 *
 * @author Lion Li
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    /**
     * 权限接口实现(使用bean注入方便用户替换)
     */
    @Bean
    public StpInterface stpInterface() {
        return new SaPermissionImpl();
    }

    /**
     * 自定义dao层存储
     */
    @Bean
    public SaTokenDao saTokenDao() {
        return new PlusSaTokenDao();
    }

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。

        List<String> swaggerUrls = List.of("/doc.html","/favicon.ico", "/webjars/**", "/resources/**"
                , "/swagger-resources/**", "/swagger-ui.html/**");

        List<String> pluginStatics = List.of("/static-plugin/**");

        List loginUrls = List.of("/code", "/auth/tenant/list", "/auth/login", "/auth/logout","/iot-oss/**");
        List<String> openApiUrls = List.of( "/openapi/v1/getToken");

        List<String> excludeUrls = new ArrayList<>();
        excludeUrls.addAll(loginUrls);
        excludeUrls.addAll(pluginStatics);
        excludeUrls.addAll(swaggerUrls);
        excludeUrls.addAll(openApiUrls);

        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                .addPathPatterns("/**")
                .excludePathPatterns(excludeUrls);
    }
}

///*
// *
// *  * | Licensed 未经许可不能去掉「OPENIITA」相关版权
// *  * +----------------------------------------------------------------------
// *  * | Author: xw2sy@163.com
// *  * +----------------------------------------------------------------------
// *
// *  Copyright [2024] [OPENIITA]
// *
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// * /
// */
//
//package cc.iotkit.common.web.interceptor;
//
//import cn.dev33.satoken.context.SaHolder;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * 新增租户拦截器，拦截请求头中的租户id
// *
// * @author Tiger Chen
// * created on 2023/7/15 14:26
// */
//
//
//public class TenantInterceptor implements HandlerInterceptor {
//
//    public static final String TENANT_ID = "Tenant-Id";
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        if (request.getHeader(TENANT_ID) != null) {
//            Long tenantId = Long.valueOf(request.getHeader(TENANT_ID));
//            SaHolder.getStorage().set("tenantId", tenantId);
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//    }
//}

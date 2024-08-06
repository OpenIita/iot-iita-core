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
package cc.iotkit.script;


import cc.iotkit.common.utils.JsonUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import java.io.IOException;

/**
 * @author sjg
 */
@Slf4j
public class JavaScriptEngine implements IScriptEngine {
    private ThreadLocal<Context> contextThreadLocal = new ThreadLocal<>();

    private ThreadLocal<Value> jsScriptThreadLocal = new ThreadLocal<>();

    private String scriptContext;

    @Override
    public void setScript(String script) {
        getContext();
        scriptContext = script;
        getJsScript();
    }

    private Context getContext() {
        Context context = contextThreadLocal.get();
        if (context == null) {
            context = Context.newBuilder("js").allowHostAccess(HostAccess.ALL).build();
            contextThreadLocal.set(context);
        }
        return context;
    }

    @Override
    public void putScriptEnv(String key, Object value) {
        Context context = getContext();
        context.getBindings("js").putMember(key, value);
    }

    @Override
    public void invokeMethod(String methodName, Object... args) {
        invokeMethod(new TypeReference<Void>() {
        }, methodName, args);
    }

    @Override
    public <T> T invokeMethod(TypeReference<T> type, String methodName, Object... args) {
        Value jsScript = getJsScript();
        Value member = jsScript.getMember("invoke");

        StringBuilder sbArgs = formatArgs(args);
        //通过调用invoke方法将目标方法返回结果转成json
        Value rst = member.execute(methodName, args);
        String json = rst.asString();
        log.info("invoke script={}, args={}, result={}", methodName, sbArgs, json);

        //没有返回值
        if (json == null || "null".equals(json)) {
            return null;
        }
        return JsonUtils.parseObject(json, type);
    }

    private Value getJsScript() {
        Value jsScript = jsScriptThreadLocal.get();
        if (jsScript == null) {
            jsScript = getContext().eval("js", String.format(
                    "new (function () {\n%s; " +
                            "   this.invoke=function(f,args){" +
                            "       for(i in args){" +
                            "           args[i]=JSON.parse(args[i]);" +
                            "       }" +
                            "       return JSON.stringify(this[f].apply(this,args));" +
                            "   }; " +
                            "})()", scriptContext));
            jsScriptThreadLocal.set(jsScript);
        }
        return jsScript;
    }

    @Override
    public String invokeMethod(String methodName, String args) {
        Value jsScript = getJsScript();
        Value member = jsScript.getMember("invoke");
        //通过调用invoke方法将目标方法返回结果转成json
        Value rst = member.execute(methodName, JsonUtils.parseArray(args, Object.class));
        String json = rst.asString();
        log.info("invoke script={}, args={}, result={}", methodName, args, json);
        //没有返回值
        if (json == null || "null".equals(json)) {
            return null;
        }
        return json;
    }

    private static StringBuilder formatArgs(Object[] args) {
        StringBuilder sbArgs = new StringBuilder("[");
        //将入参转成json
        for (int i = 0; i < args.length; i++) {
            args[i] = JsonUtils.toJsonString(args[i]);
            sbArgs.append(args[i]).append(i != args.length - 1 ? "," : "");
        }
        sbArgs.append("]");
        return sbArgs;
    }

}

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

package cc.iotkit.plugin.core;

import cc.iotkit.common.utils.StringUtils;
import cc.iotkit.common.utils.file.FileUtils;
import cc.iotkit.script.IScriptEngine;
import cc.iotkit.script.ScriptEngineFactory;

import java.net.URL;
import java.nio.charset.Charset;

/**
 * 本地独立运行的插件脚本实现
 *
 * @author sjg
 */
public class LocalPluginScript implements IPluginScript {

    private IScriptEngine scriptEngine;

    public LocalPluginScript(String scriptPath) {
        if (StringUtils.isBlank(scriptPath)) {
            return;
        }
        URL resource = LocalPluginScript.class.getClassLoader().getResource(scriptPath);
        if (resource == null) {
            return;
        }

        String script = FileUtils.readString(resource.getFile(), Charset.defaultCharset());
        initScriptEngine(script);
    }

    public IScriptEngine initScriptEngine(String script) {
        if (StringUtils.isBlank(script)) {
            return null;
        }

        scriptEngine = ScriptEngineFactory.getJsEngine(script);
        return scriptEngine;
    }

    @Override
    public IScriptEngine getScriptEngine(String pluginId) {
        return scriptEngine;
    }

    @Override
    public void reloadScript(String pluginId) {
    }
}

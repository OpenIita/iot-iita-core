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

package cc.iotkit.test;

import cc.iotkit.common.oss.core.OssClient;
import cc.iotkit.common.oss.entity.UploadResult;
import cc.iotkit.common.oss.properties.OssProperties;
import cn.hutool.core.io.FileUtil;

public class EmbedOssTest {

    public static void main(String[] args) {
        OssClient ossClient = new OssClient("", OssProperties.builder()
                .endpoint(String.format("localhost:%d/iot-oss", 8086))
                .domain("")
                .secretKey("admin")
                .accessKey("123")
                .bucketName("iot")
                .accessPolicy("1")
                .region("local")
                .build());
        UploadResult upload = ossClient.upload(FileUtil.readBytes("/Users/sjg/Downloads/b1a35946f341db92402f09529c5e7c9c.jpg"), "13%2Fimg2.png", "image/jpeg");
        System.out.println(upload);

        String privateUrl = ossClient.getPrivateUrl("img2.png", 10);
        System.out.println(privateUrl);
    }

}

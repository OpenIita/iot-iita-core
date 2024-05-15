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

package cc.iotkit.s3mock.service;

import cc.iotkit.s3mock.store.BucketStore;
import cc.iotkit.s3mock.store.MultipartStore;
import cc.iotkit.s3mock.store.ObjectStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

  @Bean
  BucketService bucketService(BucketStore bucketStore, ObjectStore objectStore) {
    return new BucketService(bucketStore, objectStore);
  }

  @Bean
  ObjectService objectService(BucketStore bucketStore, ObjectStore objectStore) {
    return new ObjectService(bucketStore, objectStore);
  }

  @Bean
  MultipartService multipartService(BucketStore bucketStore, MultipartStore multipartStore) {
    return new MultipartService(bucketStore, multipartStore);
  }
}

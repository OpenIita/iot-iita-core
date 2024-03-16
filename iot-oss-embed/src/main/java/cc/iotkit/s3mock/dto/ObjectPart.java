/*
 *  Copyright 2017-2023 Adobe.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package cc.iotkit.s3mock.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

/**
 * <a href="https://docs.aws.amazon.com/AmazonS3/latest/API/API_ObjectPart.html">API Reference</a>.
 */
@Data
@JsonRootName("ObjectPart")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ObjectPart {
    @JsonProperty("ChecksumCRC32")
    private String checksumCRC32;
    @JsonProperty("ChecksumCRC32C")
    private String checksumCRC32C;
    @JsonProperty("ChecksumSHA1")
    private String checksumSHA1;
    @JsonProperty("ChecksumSHA256")
    private String checksumSHA256;
    @JsonProperty("Size")
    private Long size;
    @JsonProperty("PartNumber")
    private Integer partNumber;
}

/*
 *   Copyright 1999-2018 dragonshard.net.
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *        http://www.apache.org/licenses/LICENSE-2.0
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package net.dragonshard.dsf.id.generator.configuration.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 本地ID配置
 *
 * @author mayee
 * @date 2019-07-08
 *
 * @version v1.0
 **/
@Component
@ConfigurationProperties(prefix = "dragonshard.id-generator.local")
@Data
public class LocalIdGeneratorProperties {
    /**
     * 是否开启
     */
    private boolean enabled;

    /**
     * 数据中心标识
     */
    private Long dataCenter = 1L;

    /**
     * 机器标识
     */
    private Long machine = 1L;


}

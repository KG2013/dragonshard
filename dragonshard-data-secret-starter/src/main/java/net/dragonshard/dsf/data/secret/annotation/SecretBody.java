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

package net.dragonshard.dsf.data.secret.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.security.Provider;

/**
 * 请求加密注解
 *
 * @author mayee
 * @date 2019-06-25
 *
 * @version v1.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface SecretBody {

    boolean exclude() default false;

    /**
     * 加密算法，AES/RSA
     */
    String value();

    /**
     * 密文类型，BASE64/HEX
     */
    String ciphertextType() default "BASE64";

    /**
     * 供应商的完整类名称
     */
    Class<? extends Provider>[] providerClass() default {};
}

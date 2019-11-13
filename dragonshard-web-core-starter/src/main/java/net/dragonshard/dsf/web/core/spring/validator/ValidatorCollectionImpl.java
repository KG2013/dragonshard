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

package net.dragonshard.dsf.web.core.spring.validator;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import org.springframework.util.Assert;

/**
 * 拓展SpringValidatorAdapter以便于校验List<T>类型数据 {@link org.springframework.validation.beanvalidation.SpringValidatorAdapter
 *
 * @author Caratacus
 */
public class ValidatorCollectionImpl implements javax.validation.Validator {

  private final javax.validation.Validator targetValidator = Validation
    .buildDefaultValidatorFactory().getValidator();

  //---------------------------------------------------------------------
  // Implementation of JSR-303 Validator interface
  //---------------------------------------------------------------------
  @Override
  @SuppressWarnings("unchecked")
  public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {
    Assert.state(this.targetValidator != null, "No target Validator set");
    if (object instanceof Collection) {
      Set<ConstraintViolation<T>> constraintViolations = new HashSet<>();
      Collection collection = (Collection) object;
      for (Object o : collection) {
        constraintViolations.addAll(this.targetValidator.validate((T) o, groups));
      }
      return constraintViolations;
    }
    return this.targetValidator.validate(object, groups);
  }

  @Override
  public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName,
    Class<?>... groups) {
    Assert.state(this.targetValidator != null, "No target Validator set");
    return this.targetValidator.validateProperty(object, propertyName, groups);
  }

  @Override
  public <T> Set<ConstraintViolation<T>> validateValue(
    Class<T> beanType, String propertyName, Object value, Class<?>... groups) {
    Assert.state(this.targetValidator != null, "No target Validator set");
    return this.targetValidator.validateValue(beanType, propertyName, value, groups);
  }

  @Override
  public BeanDescriptor getConstraintsForClass(Class<?> clazz) {
    Assert.state(this.targetValidator != null, "No target Validator set");
    return this.targetValidator.getConstraintsForClass(clazz);
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T unwrap(Class<T> type) {
    Assert.state(this.targetValidator != null, "No target Validator set");
    try {
      return (type != null ? this.targetValidator.unwrap(type) : (T) this.targetValidator);
    } catch (ValidationException ex) {
      // ignore if just being asked for plain Validator
      if (javax.validation.Validator.class == type) {
        return (T) this.targetValidator;
      }
      throw ex;
    }
  }

  @Override
  public ExecutableValidator forExecutables() {
    return targetValidator.forExecutables();
  }

}

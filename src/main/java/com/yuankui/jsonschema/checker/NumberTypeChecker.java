package com.yuankui.jsonschema.checker;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class NumberTypeChecker implements TypeChecker {

  @Override
  public boolean init(Object type) {
    return "number".equals(type);
  }

  @Override
  public CheckResult checkObject(Object object) {
    if (object instanceof Number) {
      return CheckResult.success();
    }

    return CheckResult.create("value not number");
  }
}

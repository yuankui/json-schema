package com.yuankui.jsonschema.checker;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class StringTypeChecker implements TypeChecker {

  @Override
  public boolean init(Object type) {
    return "string".equals(type);
  }

  @Override
  public CheckResult checkObject(Object object) {
    if (object instanceof String) {
      return CheckResult.success();
    }

    return CheckResult.create("value not string");
  }
}

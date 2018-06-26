package com.yuankui.jsonschema.checker;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class BooleanTypeChecker implements TypeChecker {

  @Override
  public boolean init(Object type) {
    return "boolean".equals(type);
  }

  @Override
  public CheckResult checkObject(Object object) {
    if (object instanceof Boolean) {
      return CheckResult.success();
    }
    return CheckResult.create("not boolean");
  }
}

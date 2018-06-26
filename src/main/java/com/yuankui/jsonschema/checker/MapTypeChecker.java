package com.yuankui.jsonschema.checker;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Scope("prototype")
public class MapTypeChecker implements TypeChecker {

  @Override
  public boolean init(Object type) {
    return "map".equals(type);
  }

  @Override
  public CheckResult checkObject(Object object) {
    if (object instanceof Map) {
      return CheckResult.success();
    }

    return CheckResult.create("value not map");
  }
}

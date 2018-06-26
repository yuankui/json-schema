package com.yuankui.jsonschema.checker;

public interface TypeChecker {

  boolean init(Object type);

  CheckResult checkObject(Object object);
}

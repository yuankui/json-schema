package com.yuankui.jsonschema.checker;

import lombok.Getter;

@Getter
public class CheckResult {

  private static CheckResult successResult = new CheckResult(true);
  private boolean success;
  private Object key;
  private CheckResult innerCheckResult;
  private String message;

  private CheckResult(boolean success) {
    this.success = success;
  }

  public static CheckResult create(String message) {
    CheckResult checkResult = new CheckResult(false);
    checkResult.message = message;
    return checkResult;
  }

  public static CheckResult create(Object key, CheckResult innerCheckResult) {
    CheckResult checkResult = new CheckResult(false);
    checkResult.key = key;
    checkResult.innerCheckResult = innerCheckResult;
    return checkResult;
  }

  public static CheckResult success() {
    return successResult;
  }

  @Override
  public String toString() {
    if (success) {
      return "success";
    }

    if (innerCheckResult == null) {
      return message;
    }
    
    return key + ":" + innerCheckResult.toString();
  }
  
}
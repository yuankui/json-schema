package com.yuankui.jsonschema.checker;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.annotation.Resource;

@Component
public class CheckFactory {
  
  @Resource
  private ApplicationContext context;
  
  public TypeChecker create(Object type) {
    Map<String, TypeChecker> beansOfType = context.getBeansOfType(TypeChecker.class);

    for (TypeChecker typeChecker : beansOfType.values()) {
      if (typeChecker.init(type)) {
        return typeChecker;
      }
    }

    throw new RuntimeException("no type checker found");
  }
}

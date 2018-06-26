package com.yuankui.jsonschema.checker;

import com.alibaba.fastjson.JSONObject;
import com.yuankui.jsonschema.checker.schema.ObjectSchema;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

@Component
@Scope("prototype")
public class ObjectTypeChecker implements TypeChecker {

  private ObjectSchema schema;

  private Map<String, TypeChecker> fieldCheckers;

  @Resource
  private CheckFactory checkFactory;

  @Override
  public boolean init(Object type) {
    if (!(type instanceof Map)) {
      return false;
    }

    Object typeString = ((Map) type).get("type");
    if (!"object".equals(typeString)) {
      return false;
    }

    this.schema = new JSONObject((Map<String, Object>) type).toJavaObject(ObjectSchema.class);

    this.fieldCheckers = initFieldCheckers(schema);
    return true;
  }

  private Map<String, TypeChecker> initFieldCheckers(ObjectSchema schema) {
    return schema.getSchema()
        .entrySet()
        .stream()
        .collect(
            Collectors.toMap(
                x -> x.getKey(),
                x -> checkFactory.create(x.getValue())));
  }

  @Override
  public CheckResult checkObject(Object object) {
    if (object == null) {
      return CheckResult.success();
    }

    if (!(object instanceof Map)) {
      return CheckResult.create("value not and object");
    }

    Map map = (Map) object;

    for (Map.Entry<String, TypeChecker> kv : fieldCheckers.entrySet()) {
      String fieldName = kv.getKey();
      TypeChecker checker = kv.getValue();
      Object value = map.get(fieldName);
      CheckResult result = checker.checkObject(value);
      if (!result.isSuccess()) {
        return CheckResult.create(fieldName, result);
      }
    }

    return CheckResult.success();
  }
}

package com.yuankui.jsonschema.checker;

import com.alibaba.fastjson.JSONObject;
import com.yuankui.jsonschema.checker.schema.ListSchema;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Component
@Scope("prototype")
public class ListTypeChecker implements TypeChecker {

  private ListSchema schema;
  
  private TypeChecker innerTypeCheker;

  @Resource
  private CheckFactory checkFactory;
  
  @Override
  public boolean init(Object type) {
    if (!(type instanceof Map)) {
      return false;
    }

    Object typeString = ((Map) type).get("type");

    if (!"list".equals(typeString)) {
      return false;
    }

    this.schema = new JSONObject((Map) type).toJavaObject(ListSchema.class);
    this.innerTypeCheker = checkFactory.create(this.schema.getInnerType());
    return true;
  }

  @Override
  public CheckResult checkObject(Object object) {
    if (!(object instanceof List)) {
      CheckResult.create("value not list");
    }

    List list = (List) object;

    for (int i = 0; i < list.size(); i++) {
      Object obj = list.get(i);
      CheckResult result = innerTypeCheker.checkObject(obj);
      if (!result.isSuccess()) {
        return CheckResult.create(i, result);
      }
    }
    
    return CheckResult.success();
  }
}

package com.yuankui.jsonschema.checker.schema;

import java.util.Map;

import lombok.Data;

@Data
public class ObjectSchema {
  private String type;
  private String objectName;
  private Map<String, Object> schema;
}

package com.yuankui.jsonschema.checker.schema;

import lombok.Data;

@Data
public class ListSchema {
  private String type;
  private Object innerType;
}
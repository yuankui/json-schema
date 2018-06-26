package com.yuankui.jsonschema.checker;

import com.alibaba.fastjson.JSON;
import com.yuankui.jsonschema.JsonSchemaApplication;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class ObjectTypeCheckerTest {

  @Test
  public void checkObject() {
    ApplicationContext context = 
        new AnnotationConfigApplicationContext(JsonSchemaApplication.class);

    CheckFactory checkFactory = context.getBean(CheckFactory.class);

    //language=JSON
    String schema = "{\n"
                    + "  \"type\": \"object\",\n"
                    + "  \"objectName\": \"com.youzan.ebiz.trade.api.manage.detail.dto.req.OrderResultDTO\",\n"
                    + "  \"schema\": {\n"
                    + "    \"mainOrderInfo\": {\n"
                    + "      \"type\": \"object\",\n"
                    + "      \"objectName\": \"com.youzan.ebiz.trade.api.manage.detail.dto.res.MainOrderInfoDTO\",\n"
                    + "      \"schema\": {\n"
                    + "        \"orderId\": \"number\",\n"
                    + "        \"kdtId\": \"number\",\n"
                    + "        \"orderNo\": \"string\",\n"
                    + "        \"progressBar\": {\n"
                    + "          \"type\": \"list\",\n"
                    + "          \"innerType\": {\n"
                    + "            \"type\": \"object\",\n"
                    + "            \"objectName\": \"com.youzan.ebiz.trade.api.manage.detail.dto.order.ProgressBar\",\n"
                    + "            \"schema\": {\n"
                    + "              \"type\": \"string\",\n"
                    + "              \"state\": \"number\",\n"
                    + "              \"isArrive\": \"boolean\"\n"
                    + "            }\n"
                    + "          }\n"
                    + "        }\n"
                    + "      }\n"
                    + "    },\n"
                    + "    \"itemInfo\": {\n"
                    + "      \"type\": \"list\",\n"
                    + "      \"innerType\": {\n"
                    + "        \"type\": \"object\",\n"
                    + "        \"objectName\": \"com.youzan.ebiz.trade.api.manage.detail.dto.res.ItemInfoDTO\",\n"
                    + "        \"schema\": {\n"
                    + "          \"orderNo\": \"string\",\n"
                    + "          \"originUnitPrice\": \"number\",\n"
                    + "          \"tags\": \"map\"\n"
                    + "        }\n"
                    + "      }\n"
                    + "    }\n"
                    + "  }\n"
                    + "}";

    //language=JSON
    String obj = "{\n"
                 + "  \"mainOrderInfo\": {\n"
                 + "    \"orderId\": 321321321,\n"
                 + "    \"kdtId\": 1231,\n"
                 + "    \"orderNo\": \"E321312\",\n"
                 + "    \"progressBar\": [\n"
                 + "      {\n"
                 + "        \"type\": \"bar\",\n"
                 + "        \"state\": 5,\n"
                 + "        \"isArrive\": true\n"
                 + "      },\n"
                 + "      {\n"
                 + "        \"type\": \"bar2\",\n"
                 + "        \"state\": 6,\n"
                 + "        \"isArrive\": false\n"
                 + "      }\n"
                 + "    ]\n"
                 + "  },\n"
                 + "  \"itemInfo\": [\n"
                 + "    {\n"
                 + "      \"orderNo\": \"E21312321\",\n"
                 + "      \"originUnitPrice\": 321321,\n"
                 + "      \"tags\": {\n"
                 + "        \"key1\": \"value1\",\n"
                 + "        \"key2\": \"value2\",\n"
                 + "        \"key3\": \"value3\"\n"
                 + "      }\n"
                 + "    }\n"
                 + "  ]\n"
                 + "}";
    TypeChecker typeChecker = checkFactory.create(JSON.parseObject(schema));
    CheckResult checkResult = typeChecker.checkObject(JSON.parseObject(obj));

    System.out.println("checkResult = " + checkResult);
  }
}
# json-schema

json格式校验

## v1

### schema定义

```json
{
  "type": "object",
  "objectName": "com.youzan.ebiz.trade.api.manage.detail.dto.req.OrderResultDTO",
  "schema": {
    "mainOrderInfo": {
      "type": "object",
      "objectName": "com.youzan.ebiz.trade.api.manage.detail.dto.res.MainOrderInfoDTO",
      "schema": {
        "orderId": "number",
        "kdtId": "number",
        "orderNo": "string",
        "progressBar": {
          "type": "list",
          "innerType": {
            "type": "object",
            "objectName": "com.youzan.ebiz.trade.api.manage.detail.dto.order.ProgressBar",
            "schema": {
              "type": "string",
              "state": "number",
              "isArrive": "boolean"
            }
          }
        }
      }
    },
    "itemInfo": {
      "type": "list",
      "innerType": {
        "type": "object",
        "objectName": "com.youzan.ebiz.trade.api.manage.detail.dto.res.ItemInfoDTO",
        "schema": {
          "orderNo": "string",
          "originUnitPrice": "number",
          "tags": "map"
        }
      }
    }
  }
}
```

### object

```json
{
  "mainOrderInfo": {
    "orderId": 321321321,
    "kdtId": 1231,
    "orderNo": "E321312",
    "progressBar": [
      {
        "type": "bar",
        "state": 5,
        "isArrive": true
      },
      {
        "type": "bar2",
        "state": 6,
        "isArrive": false
      }
    ]
  },
  "itemInfo": [
    {
      "orderNo": "E21312321",
      "originUnitPrice": 321321,
      "tags": {
        "key1": "value1",
        "key2": "value2",
        "key3": "value3"
      }
    }
  ]
}
```

## v2
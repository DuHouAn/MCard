### 1、上传优惠券 token

```html
GET: 127.0.0.1:9528/upload

页面
merchantsId: 4
PassTemplateId:  d447114ffcdfd2ee2847e41b601279e6
```

验证：redis 

### 2、创建用户 

```html
POST: 127.0.0.1:9528/passbook/createuser
{
    "baseInfo": {
        "name": "imooc2",
        "age": 10,
        "sex": "m"
    },
    "otherInfo": {
        "phone": "1234567890",
        "address": "北京市海淀区"
    }
}
```

执行后返回的结果 ： 用户 id --  294111

```html
{
  "errorCode": 0,
  "errorMsg": "",
  "data": {
    "id": 294111,
    "baseInfo": {
      "name": "imooc2",
      "age": 10,
      "sex": "m"
    },
    "otherInfo": {
      "phone": "1234567890",
      "address": "Beijing"
    }
  }
}
```

### 3、库存信息

```html
GET: 127.0.0.1:9528/passbook/inventoryinfo?userId=294111
```



scan 'pa:user'

结果：

```html
{
  "errorCode": 0,
  "errorMsg": "",
  "data": {
    "userId": 294111,
    "passTemplateInfos": [
      {
        "passTemplate": {
          "id": 4,
          "title": "慕课网优惠券-1",
          "summary": "优惠券简介",
          "desc": "慕课网优惠券",
          "limit": 1000,
          "hasToken": false,
          "background": 1,
          "start": 1556640000000,
          "end": 1561824000000
        },
        "merchants": {
          "id": 4,
          "name": "慕课网",
          "logoUrl": "www.imooc.com",
          "businessLicenseUrl": "www.imooc.com",
          "phone": "1234567890",
          "address": "北京市朝阳区",
          "isAudit": true
        }
      },
      {
        "passTemplate": {
          "id": 4,
          "title": "慕课网优惠券-2",
          "summary": "优惠券简介",
          "desc": "慕课网优惠券",
          "limit": 1000,
          "hasToken": true,
          "background": 1,
          "start": 1556640000000,
          "end": 1561824000000
        },
        "merchants": {
          "id": 4,
          "name": "慕课网",
          "logoUrl": "www.imooc.com",
          "businessLicenseUrl": "www.imooc.com",
          "phone": "1234567890",
          "address": "北京市朝阳区",
          "isAudit": true
        }
      }
    ]
  }
}
```

### 4、获取优惠券 

```html
-- 获取的是带有 token 的优惠券
POST: 127.0.0.1:9528/passbook/gainpasstemplate
{
    "userId": 294111,
    "passTemplate": {
        "id": 4,
        "title": "慕课网优惠券-2",
        "hasToken": true
    }
}
```

scan 'pa:pass'

### 5、userpassinfo

```html
GET: 127.0.0.1:9528/passbook/userpassinfo?userId=294111
```

输出结果：

```html
{
  "errorCode": 0,
  "errorMsg": "",
  "data": [
    {
      "pass": {
        "userId": 294111,
        "rowKey": "1114929223370479011098986d447114ffcdfd2ee2847e41b601279e6",
        "templateId": "d447114ffcdfd2ee2847e41b601279e6",
        "token": "token-1",
        "assignedDate": 1557763200000,
        "conDate": null
      },
      "passTemplate": {
        "id": 4,
        "title": "慕课网优惠券-2",
        "summary": "优惠券简介",
        "desc": "慕课网优惠券",
        "limit": 999,
        "hasToken": true,
        "background": 1,
        "start": 1556640000000,
        "end": 1561824000000
      },
      "merchants": {
        "id": 4,
        "name": "慕课网",
        "logoUrl": "www.imooc.com",
        "businessLicenseUrl": "www.imooc.com",
        "phone": "1234567890",
        "address": "北京市朝阳区",
        "isAudit": true
      }
    }
  ]
}
```

### 6、userusedpassinfo

```html
GET: 127.0.0.1:9528/passbook/userusedpassinfo?userId=294111
```

输出结果：

```html
{
  "errorCode": 0,
  "errorMsg": "",
  "data": []
}
```



### 7、userusepass

```html
POST: 127.0.0.1:9528/passbook/userusepass
{
    "userId": 294111,
    "templateId": "d447114ffcdfd2ee2847e41b601279e6"
}
```

### 8、创建评论信息

```html
POST: 127.0.0.1:9528/passbook/createfeedback
{
    "userId": 294111,
    "type": "app",
    "templateId": -1,
    "comment": "来慕课网学习分布式卡包应用"
}
{
    "userId": 294111,
    "type": "pass",
    "templateId": "e3ec06eaacb2f1dca901556991df7bb0",
    "comment": "来慕课网学习分布式卡包应用"
}
```

### 9、查看评论信息

```html
GET: 127.0.0.1:9528/passbook/getfeedback?userId=294111
```


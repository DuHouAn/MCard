<div align="center"><h1>优惠券系统实现</h1></div>

## 开发环境

- 基础工具：Maven、JDK8、[HBase](https://duhouan.github.io/2019/05/20/HBase%20%E5%AD%98%E5%82%A8%E5%8E%9F%E7%90%86%E5%88%86%E6%9E%90/)、[Kafka](https://duhouan.github.io/2019/05/19/Kafka/)、[MySQL](https://github.com/IvanLu1024/MyCards/blob/master/notes/database.md)
- 开发工具：Intellij IDEA
- 缓存：[Redis](https://duhouan.github.io/2019/05/24/Redis/)
- 项目架构：SpringBoot

## 需求分析

- [卡包应用概览](https://github.com/DuHouAn/MCard/blob/master/notes/%E9%9C%80%E6%B1%82%E5%88%86%E6%9E%90.md#%E5%8D%A1%E5%8C%85%E5%BA%94%E7%94%A8%E6%A6%82%E8%A7%88)
- [商户开放平台](https://github.com/DuHouAn/MCard/blob/master/notes/%E9%9C%80%E6%B1%82%E5%88%86%E6%9E%90.md#%E5%95%86%E6%88%B7%E5%BC%80%E6%94%BE%E5%B9%B3%E5%8F%B0)
- [用户使用入口](https://github.com/DuHouAn/MCard/blob/master/notes/%E9%9C%80%E6%B1%82%E5%88%86%E6%9E%90.md#%E7%94%A8%E6%88%B7%E4%BD%BF%E7%94%A8%E5%85%A5%E5%8F%A3)

## 技术架构

- [应用架构设计](https://github.com/DuHouAn/MCard/blob/master/notes/%E6%8A%80%E6%9C%AF%E6%9E%B6%E6%9E%84.md#%E5%BA%94%E7%94%A8%E6%9E%B6%E6%9E%84%E8%AE%BE%E8%AE%A1)

- [缓存层设计](https://github.com/DuHouAn/MCard/blob/master/notes/%E6%8A%80%E6%9C%AF%E6%9E%B6%E6%9E%84.md#%E7%BC%93%E5%AD%98%E5%B1%82%E8%AE%BE%E8%AE%A1)

- [表结构设计](https://github.com/DuHouAn/MCard/blob/master/notes/%E6%8A%80%E6%9C%AF%E6%9E%B6%E6%9E%84.md#%E8%A1%A8%E7%BB%93%E6%9E%84%E8%AE%BE%E8%AE%A1)

## 商户子系统

> **功能实现**

- 商户注册功能实现
- 优惠券投放功能实现

> **实现步骤**

- [工程环境搭建](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%B7%A5%E7%A8%8B%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA)
- [系统配置](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E7%B3%BB%E7%BB%9F%E9%85%8D%E7%BD%AE)
- [常量定义](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%B8%B8%E9%87%8F%E5%AE%9A%E4%B9%89)
- [权限校验](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E6%9D%83%E9%99%90%E6%A0%A1%E9%AA%8C)
- [实体对象定义](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%AE%9E%E4%BD%93%E5%AF%B9%E8%B1%A1%E5%AE%9A%E4%B9%89)
- [服务接口**值对象**定义](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E6%9C%8D%E5%8A%A1%E6%8E%A5%E5%8F%A3%E5%80%BC%E5%AF%B9%E8%B1%A1%E5%AE%9A%E4%B9%89)
- [商户信息服务接口](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%95%86%E6%88%B7%E4%BF%A1%E6%81%AF%E6%9C%8D%E5%8A%A1%E6%8E%A5%E5%8F%A3)
- [实现 Controller](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%AE%9E%E7%8E%B0-controller)
- [实现拦截器](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%AE%9E%E7%8E%B0%E6%8B%A6%E6%88%AA%E5%99%A8)
- [请求验证](https://github.com/DuHouAn/MCard/blob/master/notes/%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E8%AF%B7%E6%B1%82%E9%AA%8C%E8%AF%81)

## 用户子系统

> **功能实现**

- 优惠券库存功能实现
- 优惠优惠券功能实现
  * 可用优惠券功能实现
  * 已使用优惠券功能实现
  * 用户所有的优惠券
  * 用户使用优惠券
- 领取优惠券功能实现
- 评论功能实现

> **实现步骤**

- [工程环境搭建](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%B7%A5%E7%A8%8B%E7%8E%AF%E5%A2%83%E6%90%AD%E5%BB%BA)
- [资源配置](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E8%B5%84%E6%BA%90%E9%85%8D%E7%BD%AE)
- [常量定义](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%B8%B8%E9%87%8F%E5%AE%9A%E4%B9%89)
- [商户实体对象定义](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%95%86%E6%88%B7%E5%AE%9E%E4%BD%93%E5%AF%B9%E8%B1%A1%E5%AE%9A%E4%B9%89)
- [日志模块](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E6%97%A5%E5%BF%97%E6%A8%A1%E5%9D%97)
- [HBase 表值对象定义](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#hbase-%E8%A1%A8%E5%80%BC%E5%AF%B9%E8%B1%A1%E5%AE%9A%E4%B9%89)
- [HBase 表记录到 Java 对象的映射（ORM）](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#hbase-%E8%A1%A8%E8%AE%B0%E5%BD%95%E5%88%B0-java-%E5%AF%B9%E8%B1%A1%E7%9A%84%E6%98%A0%E5%B0%84orm)
- [生成 HBase 表相关 RowKey](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E7%94%9F%E6%88%90-hbase-%E8%A1%A8%E7%9B%B8%E5%85%B3%E7%9A%84-rowkey)
- [全局捕获异常](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%85%A8%E5%B1%80%E5%BC%82%E5%B8%B8%E6%8D%95%E8%8E%B7)
- [Kafka 消费者功能实现](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#kafka-%E6%B6%88%E8%B4%B9%E8%80%85%E5%8A%9F%E8%83%BD%E5%AE%9E%E7%8E%B0)
- [用户服务](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E7%94%A8%E6%88%B7%E6%9C%8D%E5%8A%A1)
- [评论服务](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E8%AF%84%E8%AE%BA%E6%9C%8D%E5%8A%A1)
- [应用服务](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%BA%94%E7%94%A8%E6%9C%8D%E5%8A%A1)
- [优惠券 token 存入 Redis](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E4%BC%98%E6%83%A0%E5%88%B8-token-%E5%AD%98%E5%85%A5-redis)
- [应用服务 HTTP 接口实现](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E5%BA%94%E7%94%A8%E6%9C%8D%E5%8A%A1-http-%E6%8E%A5%E5%8F%A3%E5%AE%9E%E7%8E%B0)
- [用户入口 HTTP 接口实现](https://github.com/DuHouAn/MCard/blob/master/notes/%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F.md#%E7%94%A8%E6%88%B7%E5%85%A5%E5%8F%A3-http-%E6%8E%A5%E5%8F%A3%E5%AE%9E%E7%8E%B0)

## 测试运行

- 测试工具：Postman

- [商户子系统测试](https://github.com/DuHouAn/MCard/blob/master/notes/%E6%B5%8B%E8%AF%95%E8%BF%90%E8%A1%8C.md#%E5%95%86%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F%E6%B5%8B%E8%AF%95)
- [用户子系统测试](https://github.com/DuHouAn/MCard/blob/master/notes/%E6%B5%8B%E8%AF%95%E8%BF%90%E8%A1%8C.md#%E7%94%A8%E6%88%B7%E5%AD%90%E7%B3%BB%E7%BB%9F%E6%B5%8B%E8%AF%95)

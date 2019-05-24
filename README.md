<div align="center"><h1>仿小米卡包后台的分布式优惠券系统实现</h1></div>

## 开发环境

- 基础工具：Maven、JDK8、[HBase](https://duhouan.github.io/2019/05/20/HBase%20%E5%AD%98%E5%82%A8%E5%8E%9F%E7%90%86%E5%88%86%E6%9E%90/)、[Kafka](https://duhouan.github.io/2019/05/19/Kafka/)、[MySQL](https://github.com/IvanLu1024/MyCards/blob/master/notes/database.md)
- 开发工具：Intellij IDEA
- 缓存：[Redis](https://duhouan.github.io/2019/05/24/Redis/)
- 项目架构：SpringBoot

## 需求分析

### 卡包应用概览

> 什么是卡包应用

卡券收集、聚合类应用。

手机上的小米卡包（未领取）：

<div align="center"><img src="https://gitee.com/duhouan/ImagePro/raw/master/mcard/m_1.png" width="300px"/></div>

手机上的小米卡包（已领取）：

<div align="center"><img src="https://gitee.com/duhouan/ImagePro/raw/master/mcard/m_2.png" width="300px"/></div>

> 卡包应用包括的子系统

- 商户投放子系统（商户开放平台）

- 用户应用子系统（用户使用入口）

> 优惠券的使用方法

- 展示型

- 兑换型

- token 核销型：优惠券带有 token 标识

### 商户开放平台

商户接口字段：

<div align="center"><img src="https://gitee.com/duhouan/ImagePro/raw/master/mcard/m_3.png" width="650px"/></div>

优惠券接口字段：

<div align="center"><img src="https://gitee.com/duhouan/ImagePro/raw/master/mcard/m_4.png"/></div>

### 用户应用子系统

用户优惠券详细信息：

<div align="center"><img src="https://gitee.com/duhouan/ImagePro/raw/master/mcard/m_5.png" width="300px"/></div>

过期优惠券：

<div align="center"><img src="https://gitee.com/duhouan/ImagePro/raw/master/mcard/m_6.png" width="300px"/></div>

优惠券库存：展示用户未领取但是可领取的优惠券。

<div align="center"><img src="https://gitee.com/duhouan/ImagePro/raw/master/mcard/m_1.png" width="300px"/></div>

用户反馈：优惠券本身的相关问题和我的卡包这款 App 相关问题。

<div align="center"><img src="https://gitee.com/duhouan/ImagePro/raw/master/mcard/m_7.png" width="300px"/></div>

## 技术架构

> **应用架构设计**





> **缓存层设计**





> **表结构设计**



## 商户子系统

## 用户子系统

## 测试运行

- 测试工具：Postman


package com.southeast.passbook.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <h1>商户对象模型</h1>
 * Created by 18351 on 2019/5/7.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "merchants")
public class Merchants {
    /*
      `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
     `name` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商户名称',
     `logo_url` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '商户 logo',
     `business_license_url` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '商户营业执照',
     `phone` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商户联系电话',
     `address` varchar(64) COLLATE utf8_bin NOT NULL COMMENT '商户地址',
     `is_audit` BOOLEAN NOT NULL COMMENT '是否通过审核',
     */

    @Id
    @GeneratedValue //自增
    @Column(name = "id",nullable = false)
    private Integer id; //商户 id，主键

    @Basic
    @Column(name = "name",unique = true,nullable = false)
    private String name; //商户名，需要全局唯一 unique = true

    @Basic
    @Column(name ="logo_url",nullable = false)
    private String logoUrl; //商户 logo

    @Basic
    @Column(name ="business_license_url",nullable = false)
    private String businessLicenseUrl; //商户营业执照

    @Basic
    @Column(name ="phone",nullable = false)
    private String phone; //商户联系电话

    @Basic
    @Column(name ="address",nullable = false)
    private String address; //商户地址

    @Basic
    @Column(name ="is_audit",nullable = false)
    private Boolean isAudit = false; //商户是否通过审核，默认是 false
}

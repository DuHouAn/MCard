package com.southeast.passbook.dao;

import com.southeast.passbook.entity.Merchants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * <h1> Merchants 的 Dao 接口</h1>
 * Created by 18351 on 2019/5/7.
 */
public interface MerchantsDao extends JpaRepository<Merchants,Integer>{
    // JpaRepository<Merchants,Integer> 中
    // Merchants表示的是 merchants表
    // Integer表示该表的主键类型

    /**
     * 根据 id 获取商户对象
     * @param id 商户 id
     * @return {@link Merchants}
     */
    Merchants findById(Integer id);

    /**
     * 根据商户名获取商户对象
     * @param name 商户名
     * @return {@link Merchants}
     */
    Merchants findByName(String name);

    /**
     * 根据商户 ids 获取商户对象
     * @param ids 商户 ids
     * @return {@link Merchants}
     */
    List<Merchants> findByIdIn(List<Integer> ids);
}

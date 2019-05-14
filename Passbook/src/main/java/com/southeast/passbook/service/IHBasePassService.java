package com.southeast.passbook.service;

import com.southeast.passbook.vo.PassTemplate;

/**
 * <h1>Pass Hbase 服务</h1>
 * Created by 18351 on 2019/5/12.
 */
public interface IHBasePassService {

    /**
     * 将 PassTemplate 写入 Hbase
     * @param passTemplate {@link PassTemplate}
     * @return true/false
     */
    boolean dropPassTemplateToHBase(PassTemplate passTemplate);
}

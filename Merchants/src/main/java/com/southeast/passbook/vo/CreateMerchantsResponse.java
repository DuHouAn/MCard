package com.southeast.passbook.vo;

import com.southeast.passbook.constants.ErrorCode;
import com.southeast.passbook.dao.MerchantsDao;
import com.southeast.passbook.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>创建商户的响应对象</h1>
 * Created by 18351 on 2019/5/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsResponse {
    private Integer id; //商户 id: 创建失败返回 -1
}

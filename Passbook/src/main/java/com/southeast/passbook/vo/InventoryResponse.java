package com.southeast.passbook.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h1>库存响应信息</h1>
 * 可领取的优惠券
 * Created by 18351 on 2019/5/13.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {

    private Long userId; //用户 id，标识不同的用户可以看到不同的优惠券信息

    private List<PassTemplateInfo> passTemplateInfos;//优惠券模板信息
}

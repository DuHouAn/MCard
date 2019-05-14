package com.southeast.passbook.log;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>日志对象</h1>
 * Created by 18351 on 2019/5/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogObject {
    private String action; //日志动作类型，与 ActionName 中定义的常量对应

    private Long userId; //用户 id

    private Long timestamp; //当前时间戳

    private String remoteIp; //客户端 ip 地址,可以反作弊

    private Object info = null; //日志信息
}

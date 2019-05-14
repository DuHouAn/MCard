package com.southeast.passbook.mapper;

import com.southeast.passbook.constant.Constants;
import com.southeast.passbook.vo.Pass;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.commons.lang.time.DateUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

/**
 *  <h1>HBase  Pass Row To Pass Object</h1>
 * Created by 18351 on 2019/5/12.
 */
public class PassRowMapper implements RowMapper<Pass>{

    private static byte[] FAMILY_I = Constants.PassTable.FAMILY_I.getBytes();
    private static byte[] USER_ID = Constants.PassTable.USER_ID.getBytes();
    private static byte[] TEMPLATE_ID = Constants.PassTable.TEMPLATE_ID.getBytes();
    private static byte[] TOKEN = Constants.PassTable.TOKEN.getBytes();
    private static byte[] ASSIGNED_DATE = Constants.PassTable.ASSIGNED_DATE.getBytes();
    private static byte[] CON_DATE = Constants.PassTable.CON_DATE.getBytes();

    @Override
    public Pass mapRow(Result result, int i) throws Exception {

        Pass pass = new Pass();

        pass.setUserId(Bytes.toLong(result.getValue(FAMILY_I,USER_ID)));
        pass.setTemplateId(Bytes.toString(result.getValue(FAMILY_I,TEMPLATE_ID)));
        pass.setToken(Bytes.toString(result.getValue(FAMILY_I,TOKEN)));

        String[] patterns = new String[] {"yyyy-MM-dd"};
        pass.setAssignedDate(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_I,ASSIGNED_DATE)),patterns));
        String canDateStr = Bytes.toString(result.getValue(FAMILY_I,CON_DATE));
        if("-1".equals(canDateStr)){
            pass.setConDate(null);
        }else{
            pass.setConDate(DateUtils.parseDate(canDateStr,patterns));
        }

        pass.setRowKey(Bytes.toString(result.getRow()));

        return pass;
    }
}

package com.southeast.passbook.mapper;

import com.southeast.passbook.constant.Constants;
import com.southeast.passbook.vo.PassTemplate;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.commons.lang.time.DateUtils;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;


/**
 * <h1>HBase  PassTemplate Row To PassTemplate Object</h1>
 * Created by 18351 on 2019/5/12.
 */
public class PassTemplateRowMapper implements RowMapper<PassTemplate> {

    private static byte[] FAMILY_B = Constants.PassTemplateTable.FAMILY_B.getBytes();
    private static byte[] ID = Constants.PassTemplateTable.ID.getBytes();
    private static byte[] TITLE = Constants.PassTemplateTable.TITLE.getBytes();
    private static byte[] SUMMARY = Constants.PassTemplateTable.SUMMARY.getBytes();
    private static byte[] DESC = Constants.PassTemplateTable.DESC.getBytes();
    private static byte[] HAS_TOKEN = Constants.PassTemplateTable.HAS_TOKEN.getBytes();
    private static byte[] BACKGROUND = Constants.PassTemplateTable.BACKGROUND.getBytes();

    private static byte[] FAMILY_C = Constants.PassTemplateTable.FAMILY_C.getBytes();
    private static byte[] LIMIT = Constants.PassTemplateTable.LIMIT.getBytes();
    private static byte[] START = Constants.PassTemplateTable.START.getBytes();
    private static byte[] END = Constants.PassTemplateTable.END.getBytes();

    @Override
    public PassTemplate mapRow(Result result, int i) throws Exception {
        PassTemplate passTemplate = new PassTemplate();

        passTemplate.setId(Bytes.toInt(result.getValue(FAMILY_B,ID)));
        passTemplate.setTitle(Bytes.toString(result.getValue(FAMILY_B,TITLE)));
        passTemplate.setSummary(Bytes.toString(result.getValue(FAMILY_B,SUMMARY)));
        passTemplate.setDesc(Bytes.toString(result.getValue(FAMILY_B,DESC)));
        passTemplate.setHasToken(Bytes.toBoolean(result.getValue(FAMILY_B,HAS_TOKEN)));
        passTemplate.setBackground(Bytes.toInt(result.getValue(FAMILY_B,BACKGROUND)));

        String[] patterns = new String[]{"yyyy-MM-dd"}; //定义日期输出格式

        passTemplate.setLimit(Bytes.toLong(result.getValue(FAMILY_C,LIMIT)));
        passTemplate.setStart(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_C,START)),patterns));
        passTemplate.setEnd(DateUtils.parseDate(Bytes.toString(result.getValue(FAMILY_C,END)),patterns));

        return passTemplate;
    }
}

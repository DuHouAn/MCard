package com.southeast.passbook.mapper;

import com.southeast.passbook.constant.Constants;
import com.southeast.passbook.vo.Feedback;
import com.spring4all.spring.boot.starter.hbase.api.RowMapper;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;


/**
 * <h1>HBase Feedback Row To Feedback Object</h1>
 * Created by 18351 on 2019/5/12.
 */
public class FeedbackRowMapper implements RowMapper<Feedback> {

    private static byte[] FAMILY_I = Constants.Feedback.FAMILY_I.getBytes();
    private static byte[] USER_ID = Constants.Feedback.USER_ID.getBytes();
    private static byte[] TYPE = Constants.Feedback.TYPE.getBytes();
    private static byte[] TEMPLATE_ID = Constants.Feedback.TEMPLATE_ID.getBytes();
    private static byte[] COMMENT = Constants.Feedback.COMMENT.getBytes();

    @Override
    public Feedback mapRow(Result result, int i) throws Exception {
        Feedback feedback = new Feedback();

        feedback.setUserId(Bytes.toLong(result.getValue(FAMILY_I,USER_ID)));
        feedback.setType(Bytes.toString(result.getValue(FAMILY_I,TYPE)));
        feedback.setTemplateId(Bytes.toString(result.getValue(FAMILY_I,TEMPLATE_ID)));
        feedback.setComment(Bytes.toString(result.getValue(FAMILY_I,COMMENT)));

        return feedback;
    }
}

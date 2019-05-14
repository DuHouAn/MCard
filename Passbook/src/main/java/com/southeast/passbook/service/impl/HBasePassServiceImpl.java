package com.southeast.passbook.service.impl;

import com.southeast.passbook.constant.Constants;
import com.southeast.passbook.service.IHBasePassService;
import com.southeast.passbook.utils.RowKeyGenUtil;
import com.southeast.passbook.vo.PassTemplate;
import com.spring4all.spring.boot.starter.hbase.api.HbaseTemplate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 18351 on 2019/5/12.
 */
@Slf4j
@Service
public class HBasePassServiceImpl implements IHBasePassService{

    private final HbaseTemplate hbaseTemplate; // HBase 客户端

    @Autowired
    public HBasePassServiceImpl(HbaseTemplate hbaseTemplate) {
        this.hbaseTemplate = hbaseTemplate;
    }

    @Override
    public boolean dropPassTemplateToHBase(PassTemplate passTemplate) {
        if (null == passTemplate) {
            return false;
        }

        String rowKey = RowKeyGenUtil.genPassTemplateRowKey(passTemplate);

        try {
            if (hbaseTemplate.getConnection().getTable(TableName.valueOf(Constants.PassTemplateTable.TABLE_NAME))
                    .exists(new Get(Bytes.toBytes(rowKey)))) {
                log.warn("RowKey {} is already exist!", rowKey);
                return false;
            }
        } catch (Exception ex) {
            log.error("DropPassTemplateToHBase Error: {}", ex.getMessage());
            return false;
        }

        byte[] FAMILY_B = Bytes.toBytes(Constants.PassTemplateTable.FAMILY_B);
        byte[] ID = Bytes.toBytes(Constants.PassTemplateTable.ID);
        byte[] TITLE = Bytes.toBytes(Constants.PassTemplateTable.TITLE);
        byte[] SUMMARY = Bytes.toBytes(Constants.PassTemplateTable.SUMMARY);
        byte[] DESC = Bytes.toBytes(Constants.PassTemplateTable.DESC);
        byte[] HAS_TOKEN = Bytes.toBytes(Constants.PassTemplateTable.HAS_TOKEN);
        byte[] BACKGROUND = Bytes.toBytes(Constants.PassTemplateTable.BACKGROUND);

        byte[] FAMILY_C = Bytes.toBytes(Constants.PassTemplateTable.FAMILY_C);
        byte[] LIMIT = Bytes.toBytes(Constants.PassTemplateTable.LIMIT);
        byte[] START = Bytes.toBytes(Constants.PassTemplateTable.START);
        byte[] END = Bytes.toBytes(Constants.PassTemplateTable.END);

        Put put = new Put(Bytes.toBytes(rowKey));

        put.addColumn(FAMILY_B, ID, Bytes.toBytes(passTemplate.getId()));
        put.addColumn(FAMILY_B, TITLE, Bytes.toBytes(passTemplate.getTitle()));
        put.addColumn(FAMILY_B, SUMMARY, Bytes.toBytes(passTemplate.getSummary()));
        put.addColumn(FAMILY_B, DESC, Bytes.toBytes(passTemplate.getDesc()));
        put.addColumn(FAMILY_B, HAS_TOKEN, Bytes.toBytes(passTemplate.getHasToken()));
        put.addColumn(FAMILY_B, BACKGROUND, Bytes.toBytes(passTemplate.getBackground()));

        put.addColumn(FAMILY_C, LIMIT, Bytes.toBytes(passTemplate.getLimit()));
        put.addColumn(FAMILY_C, START, Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(passTemplate.getStart())));
        put.addColumn(FAMILY_C, END, Bytes.toBytes(DateFormatUtils.ISO_DATE_FORMAT.format(passTemplate.getEnd())));

        hbaseTemplate.saveOrUpdate(Constants.PassTemplateTable.TABLE_NAME,put);

        return false;
    }
}

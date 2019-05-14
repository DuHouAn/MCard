package com.southeast.passbook.vo;

import com.google.common.base.Enums;
import com.southeast.passbook.constant.FeedbackType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <h1>用户评论</h1>
 * Created by 18351 on 2019/5/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    private Long userId; //用户 id

    private String type;//评论类型

    private String templateId; //PassTemplate RowKey, 如果是 app 类型的评论, 则没有

    private String comment; //评论内容

    public boolean validate() {

        FeedbackType feedbackType = Enums.getIfPresent(
                FeedbackType.class, this.type.toUpperCase()
        ).orNull();

        //反馈类型为 null 或者未评论都不能提交
        return !(null == feedbackType || null == comment);
    }
}

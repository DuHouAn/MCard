package com.southeast.passbook.service;

import com.southeast.passbook.vo.Feedback;
import com.southeast.passbook.vo.Response;

/**
 * <h1>用户评论功能</h1>
 * Created by 18351 on 2019/5/13.
 */
public interface IFeedbackService {

    /**
     * 创建评论
     * @param feedback {@link Feedback}
     * @return {@link Response}
     */
    Response createFeedback(Feedback feedback);

    /**
     * 获取用户评论
     * @param userId 用户 id
     * @return {@link Response}
     */
    Response getFeedback(Long userId);
}

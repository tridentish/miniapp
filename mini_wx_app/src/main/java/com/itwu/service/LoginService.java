package com.itwu.service;

import com.itwu.entity.R;
import com.itwu.entity.User;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface LoginService {
    /**
     * 登录
     *
     * @param
     * @return
     */
    R login(User user);

    /**
     * 检查token
     *
     * @param token
     * @return
     */
    R checkToken(String token);

    /**
     * 退出登录
     *
     * @param token
     * @return
     */
    R logout(String token);

    /**
     * 注册
     *
     * @param loginParams
     * @return
     */

    R register(User user);
}

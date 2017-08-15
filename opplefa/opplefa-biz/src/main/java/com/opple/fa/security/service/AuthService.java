package com.opple.fa.security.service;

import com.opple.fa.security.entity.User;

import java.util.List;

/**
 * Created by Phoenix Z Sun on 9/25/2016.
 */
public interface AuthService {
    /**
     *  获取用户拥有权限的按钮
     * @param user
     * @return
     */
    List<String> getAuthButtons(User user);

}

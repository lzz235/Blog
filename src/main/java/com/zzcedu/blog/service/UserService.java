package com.zzcedu.blog.service;

import com.zzcedu.blog.util.JsonResult;

import java.security.NoSuchAlgorithmException;

/**
 * @Author liu
 * @Date 2020/12/29 17:11
 */
public interface UserService {
    /**
     * 检查用户登录
     * @param username
     * @param password
     * @return 响应实体类
     * @throws NoSuchAlgorithmException
     */
    JsonResult checkLogin(String username,String password) throws NoSuchAlgorithmException;

    JsonResult addUser(String username, String password, String nick);

    JsonResult rePwd(String lastPwd, String newPwd, String uname) throws NoSuchAlgorithmException;
}

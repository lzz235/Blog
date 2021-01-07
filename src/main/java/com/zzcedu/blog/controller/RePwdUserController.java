package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.UserService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @Author liu
 * @Date 2021/1/7 18:27
 */
@RestController
public class RePwdUserController {
    @Resource
    private UserService userService;
    @RequestMapping("/user/rePwd.do")
    public JsonResult execute(String lastPwd,String newPwd,String uname) throws NoSuchAlgorithmException {
        return userService.rePwd(lastPwd,newPwd,uname);
    }
}

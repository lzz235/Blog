package com.zzcedu.blog.service;

import com.zzcedu.blog.dao.UserDao;
import com.zzcedu.blog.entity.User;
import com.zzcedu.blog.util.JsonResult;
import com.zzcedu.blog.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;

/**
 * @Author liu
 * @Date 2020/12/29 17:13
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public JsonResult checkLogin(String username, String password) throws NoSuchAlgorithmException {
        JsonResult jsonResult = new JsonResult();
        User user = userDao.findByName(username);
        //1.账号不存在
        if (user == null){
            jsonResult.setStatus(1);
            jsonResult.setMsg("用户名不存在");
            return jsonResult;
        }
        //2.密码不正确
        String md5 = NoteUtil.MD5(password);
        if (!user.getCn_user_password().equals(md5)){
            jsonResult.setStatus(2);
            jsonResult.setMsg("密码或账号错误");
            return jsonResult;
        }
        //3.成功登录
        jsonResult.setStatus(0);
        jsonResult.setMsg("登录成功");
        //屏蔽密码操作
        user.setCn_user_password("");
        jsonResult.setData(user);
        return jsonResult;
    }
}

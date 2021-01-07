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

    @Override
    public JsonResult addUser(String username, String password, String nick) {
        JsonResult jsonResult = new JsonResult();
        //判断用户是否被占用
        User user = userDao.findByName(username);
        if (user != null){
            jsonResult.setStatus(1);
            jsonResult.setMsg("用户名被占用");
            return jsonResult;
        }
        try {
            User user1 = new User();
            //执行插入操作
            user1.setCn_user_name(username);
            user1.setCn_user_nick(nick);
            user1.setCn_user_password(NoteUtil.MD5(password));
            user1.setCn_user_id(NoteUtil.getUuid());
            userDao.save(user1);
            jsonResult.setStatus(0);
            jsonResult.setMsg("注册成功");
            return jsonResult;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonResult rePwd(String lastPwd, String newPwd, String uname) throws NoSuchAlgorithmException {
        JsonResult jsonResult = new JsonResult();
        User user = userDao.findByName(uname);
        String last = NoteUtil.MD5(lastPwd);
        String newP = NoteUtil.MD5(newPwd);
        if (!user.getCn_user_password().equals(last)){
            jsonResult.setStatus(1);
            jsonResult.setMsg("原密码错误");
            return jsonResult;
        }
        int i = userDao.updatePwd(uname,newP);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("修改密码成功");
            return jsonResult;
        }
        jsonResult.setStatus(2);
        jsonResult.setMsg("修改失败");
        return jsonResult;
    }
}

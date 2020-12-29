package com.zzcedu.blog.dao;

import com.zzcedu.blog.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liu
 * @Date 2020/12/29 15:35
 */
public interface UserDao {
    @Select("select * from cn_user")
    List<User> findALL();
}

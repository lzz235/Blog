package com.zzcedu.blog.dao;

import com.zzcedu.blog.entity.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author liu
 * @Date 2020/12/30 16:06
 */
public interface BookDao {
    /**
     * 根据用户id查询用户拥有的笔记
     * @param userId
     * @return 笔记本列表
     */
    @Select("select cn_notebook_id,cn_notebook_type_id,cn_notebook_name,cn_notebook_desc from cn_notebook where cn_user_id = #{userId}")
    List<Book> findByUserId(String userId);
}

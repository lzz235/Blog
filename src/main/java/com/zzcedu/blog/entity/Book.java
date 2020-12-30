package com.zzcedu.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Author liu
 * @Date 2020/12/30 16:03
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String cn_notebook_id;
    private String cn_user_id;
    private String cn_notebook_type_id;
    private String cn_notebook_name;
    private String cn_notebook_desc;
    private Timestamp cn_notebook_createtime;

}

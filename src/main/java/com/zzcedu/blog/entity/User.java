package com.zzcedu.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author liu
 * @Date 2020/12/29 15:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String cn_user_id;
    private String cn_user_name;
    private String cn_user_password;
    private String cn_user_token;
    private String cn_user_nick;
}

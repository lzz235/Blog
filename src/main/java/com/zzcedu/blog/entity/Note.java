package com.zzcedu.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author liu
 * @Date 2020/12/31 14:47
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Note {
 private String cn_note_id;
 private String cn_notebook_id;
 private String cn_user_id;
 private String cn_note_status_id;
 private String cn_note_type_id;
 private String cn_note_title;
 private String cn_note_body;
 private long cn_note_create_time;
 private long cn_note_last_modify_time;
}

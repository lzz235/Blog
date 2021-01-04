package com.zzcedu.blog.service;

import com.zzcedu.blog.util.JsonResult;

/**
 * @Author liu
 * @Date 2020/12/31 14:51
 */
public interface NoteService {
    /**
     * 加载笔记列表
     * @param bookId
     * @return 响应实体类
     */
    JsonResult loadNotes(String bookId);

    /**
     * 加载笔记详情
     * @param noteId
     * @return 响应实体类
     */
    JsonResult loadNote(String noteId);
}

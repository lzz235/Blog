package com.zzcedu.blog.service;

import com.zzcedu.blog.util.JsonResult;

/**
 * @Author liu
 * @Date 2020/12/30 16:11
 */
public interface BookService {
    /**
     * 根据用户id加载对应的用户笔记
     * @param userId
     * @return Json响应实体类
     */
    JsonResult loadUserBooks(String userId);
}

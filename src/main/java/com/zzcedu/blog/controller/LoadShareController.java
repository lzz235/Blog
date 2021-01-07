package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author liu
 * @Date 2021/1/7 16:37
 */
@RestController
public class LoadShareController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/loadShare.do")
    public JsonResult execute(String shareId){
        return noteService.loadShare(shareId);
    }
}

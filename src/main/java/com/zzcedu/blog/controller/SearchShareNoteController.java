package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author liu
 * @Date 2021/1/7 14:29
 */
@RestController
public class SearchShareNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/search_share.do")
    public JsonResult execute(String keyword,Integer page){
        return noteService.searchShareNote(keyword,page);
    }
}

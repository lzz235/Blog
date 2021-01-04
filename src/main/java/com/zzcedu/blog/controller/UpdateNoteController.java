package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author liu
 * @Date 2021/1/4 14:55
 */
@RestController
public class UpdateNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/update.do")
    public JsonResult execute(String title,String body,String noteId){
        return noteService.updateNote(title,body,noteId);
    }
}

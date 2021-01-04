package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author liu
 * @Date 2020/12/31 16:09
 */
@RestController
public class LoadNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/loadNote.do")
    public JsonResult execute(String noteId){
        JsonResult jsonResult = noteService.loadNote(noteId);
        return jsonResult;
    }
}

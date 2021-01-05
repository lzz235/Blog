package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author liu
 * @Date 2021/1/5 16:45
 */
@RestController
public class DeleteNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/delete.do")
    public JsonResult execute(String noteId){
        return noteService.deleteNote(noteId);
    }
}

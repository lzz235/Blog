package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.NoteService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author liu
 * @Date 2021/1/6 14:21
 */
@RestController
public class MoveNoteController {
    @Resource
    private NoteService noteService;
    @RequestMapping("/note/move.do")
    public JsonResult execute(String noteId,String bookId){
        return noteService.moveNote(noteId,bookId);
    }
}

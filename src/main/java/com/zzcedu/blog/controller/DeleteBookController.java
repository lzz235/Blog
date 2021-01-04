package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.BookService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author liu
 * @Date 2021/1/4 18:59
 */
@RestController
public class DeleteBookController {
    @Resource
    private BookService bookService;
    @RequestMapping("/book/delete.do")
    public JsonResult execute(String bookId){
        return bookService.delete(bookId);
    }
}

package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.BookService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author liu
 * @Date 2021/1/5 10:18
 */
@RestController
public class LikeBookController {
    @Resource
    private BookService bookService;
    @RequestMapping("/book/like.do")
    public JsonResult execute(String bookId){
        return bookService.like(bookId);
    }
}

package com.zzcedu.blog.controller;

import com.zzcedu.blog.service.BookService;
import com.zzcedu.blog.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author liu
 * @Date 2021/1/4 16:23
 */
@RestController
public class AddBookController {
    @Resource
    private BookService bookService;
    @RequestMapping("/book/add.do")
    public JsonResult execute(String bookName,String userId){
        return bookService.addBook(bookName,userId);
    }
}

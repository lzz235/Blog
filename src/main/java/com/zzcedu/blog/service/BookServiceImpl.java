package com.zzcedu.blog.service;

import com.zzcedu.blog.dao.BookDao;
import com.zzcedu.blog.entity.Book;
import com.zzcedu.blog.util.JsonResult;
import com.zzcedu.blog.util.NoteUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author liu
 * @Date 2020/12/30 16:12
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookDao bookDao;
    @Override
    public JsonResult loadUserBooks(String userId) {
        JsonResult jsonResult = new JsonResult();
        List<Book> books = bookDao.findByUserId(userId);
        //创建返回结果
        jsonResult.setStatus(0);
        jsonResult.setMsg("查询笔记本成功");
        jsonResult.setData(books);
        return jsonResult;
    }

    @Override
    public JsonResult addBook(String bookName, String userId) {
        JsonResult jsonResult = new JsonResult();
        Book book = Book.builder().cn_notebook_id(NoteUtil.getUuid()).cn_notebook_createtime(new Timestamp(System.currentTimeMillis()))
                .cn_notebook_name(bookName).cn_user_id(userId).cn_notebook_desc("")
                .cn_notebook_type_id("5").build();
        int i = bookDao.save(book);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("创建笔记本成功");
            jsonResult.setData(book);
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("创建笔记本失败");
        jsonResult.setData(null);
        return jsonResult;
    }

    @Override
    public JsonResult rename(String rename, String bookId) {
        JsonResult jsonResult = new JsonResult();
        Book book = Book.builder().cn_notebook_name(rename).cn_notebook_id(bookId).build();
        int i = bookDao.rename(book);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("重命名成功");
            jsonResult.setData(book);
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("重命名失败");
        return jsonResult;
    }

    @Override
    public JsonResult delete(String bookId) {
        JsonResult jsonResult = new JsonResult();
        int i = bookDao.delete(bookId);
        if (i > 0){
            jsonResult.setStatus(0);
            jsonResult.setMsg("删除笔记本成功");
            return jsonResult;
        }
        jsonResult.setStatus(1);
        jsonResult.setMsg("删除笔记本失败");
        return jsonResult;
    }
}

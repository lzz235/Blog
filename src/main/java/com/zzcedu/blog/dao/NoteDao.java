package com.zzcedu.blog.dao;

import com.zzcedu.blog.entity.Note;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author liu
 * @Date 2020/12/31 14:48
 */
public interface NoteDao {
    /**
     *根据bookId查询note
     * @param bookId
     * @return 笔记列表
     */
    @Select("select * from cn_note where cn_notebook_id = #{bookId}")
    List<Note> findByBookId(String bookId);

    /**
     * 根据noteId查询详情
     * @param noteId
     * @return 笔记详情
     */
    @Select("select * from cn_note where cn_note_id = #{noteId}")
    Note findById(String noteId);

    /**
     * 修改笔记本详情
     * @param note
     * @return
     */
    @Update("update cn_note set cn_note_title = #{cn_note_title},cn_note_body=#{cn_note_body},cn_note_last_modify_time=#{cn_note_last_modify_time} where cn_note_id = #{cn_note_id}")
    int update(Note note);
}

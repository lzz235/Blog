package com.zzcedu.blog.dao;

import com.zzcedu.blog.entity.Note;
import org.apache.ibatis.annotations.Insert;
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
    @Select("select * from cn_note where cn_notebook_id = #{bookId} and cn_note_status_id = 1")
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

    /**
     * 创建笔记
     * @param note
     * @return
     */
    @Insert("insert into cn_note(cn_note_id,cn_note_title,cn_notebook_id,cn_user_id,cn_note_status_id,cn_note_create_time" +
            ",cn_note_last_modify_time,cn_note_type_id,cn_note_body) values(#{cn_note_id},#{cn_note_title},#{cn_notebook_id},#{cn_user_id}" +
            ",#{cn_note_status_id},#{cn_note_create_time},#{cn_note_last_modify_time},#{cn_note_type_id},#{cn_note_body})")
    int save(Note note);

    /**
     * 把笔记放到回收站 把status改为2
     * @param noteId
     * @return
     */
    @Update("update cn_note set cn_note_status_id=2 where cn_note_id = #{noteId}")
    int updateStatus(String noteId);
}

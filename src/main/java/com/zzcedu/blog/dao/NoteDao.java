package com.zzcedu.blog.dao;

import com.zzcedu.blog.entity.Note;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

//    /**
//     * 修改笔记本详情(笔记名(cn_note_title)，内容(cn_note_body)，修改时间(cn_note_last_modify_time))
//     * @param note
//     * @return
//     */
//    @Update("update cn_note set cn_note_title = #{cn_note_title},cn_note_body=#{cn_note_body},cn_note_last_modify_time=#{cn_note_last_modify_time} where cn_note_id = #{cn_note_id}")
//    int update(Note note);

    /**
     * 创建笔记
     * @param note
     * @return
     */
    @Insert("insert into cn_note(cn_note_id,cn_note_title,cn_notebook_id,cn_user_id,cn_note_status_id,cn_note_create_time" +
            ",cn_note_last_modify_time,cn_note_type_id,cn_note_body) values(#{cn_note_id},#{cn_note_title},#{cn_notebook_id},#{cn_user_id}" +
            ",#{cn_note_status_id},#{cn_note_create_time},#{cn_note_last_modify_time},#{cn_note_type_id},#{cn_note_body})")
    int save(Note note);
//
//    /**
//     * 把笔记放到回收站 把status改为2
//     * @param noteId
//     * @return
//     */
//    @Update("update cn_note set cn_note_status_id=2 where cn_note_id = #{noteId}")
//    int updateStatus(String noteId);

//    /**
//     * 移动笔记 更改笔记本Id(bookId)
//     * @param noteId
//     * @param bookId
//     * @return
//     */
//    @Update("update cn_note set cn_notebook_id = #{bookId} where cn_note_id = #{noteId}")
//    int updateNote(@Param("noteId") String noteId,@Param("bookId") String bookId);

//    /**
//     * 分享笔记 更改笔记typeId(cn_note_type_id)
//     * @param noteId
//     * @return
//     */
//    @Update("update cn_note set cn_note_type_id = 2 where cn_note_id = #{noteId}")
//    int updateNoteByTypeId(String noteId);

    /**
     * 动态更新笔记
     * @param note
     * @return
     */
    @Update("<script> update cn_note\n" +
            "        <set>\n" +
            "            <if test=\"cn_notebook_id != null\">\n" +
            "                cn_notebook_id = #{cn_notebook_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_user_id != null\">\n" +
            "                cn_user_id = #{cn_user_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_status_id != null\">\n" +
            "                cn_note_status_id = #{cn_note_status_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_type_id != null\">\n" +
            "                cn_note_type_id = #{cn_note_type_id},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_title != null\">\n" +
            "                cn_note_title = #{cn_note_title},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_body != null\">\n" +
            "                cn_note_body = #{cn_note_body},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_create_time != null\">\n" +
            "                cn_note_create_time = #{cn_note_create_time},\n" +
            "            </if>\n" +
            "            <if test=\"cn_note_last_modify_time != null\">\n" +
            "                cn_note_last_modify_time = #{cn_note_last_modify_time},\n" +
            "            </if>\n" +
            "        </set>\n" +
            "        where cn_note_id = #{cn_note_id} </script>")
    int dynamicUpdate(Note note);


}

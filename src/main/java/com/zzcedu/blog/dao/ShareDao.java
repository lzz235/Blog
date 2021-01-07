package com.zzcedu.blog.dao;

import com.zzcedu.blog.entity.Share;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author liu
 * @Date 2021/1/6 16:05
 */
public interface ShareDao {
    /**
     * 插入非常笔记
     * @param share
     */
    @Insert("insert into cn_share(cn_share_id,cn_share_title,cn_share_body,cn_note_id) values(#{cn_share_id},#{cn_share_title},#{cn_share_body},#{cn_note_id})")
    void save(Share share);

    /**
     * 搜索分享笔记
     * @param keyword
     * @param start
     * @return
     */
    @Select("select * from cn_share where cn_share_title like #{keyword} limit #{start},5")
    List<Share> findLikeTitke(@Param("keyword") String keyword,@Param("start") int start);

    /**
     * 根据shareId查询详情
     * @param shareId
     * @return 分享笔记详情
     */
    @Select("select * from cn_share where cn_share_id = #{shareId}")
    Share findShareById(String shareId);
}

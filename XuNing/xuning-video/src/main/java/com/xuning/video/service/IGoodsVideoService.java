package com.xuning.video.service;

import java.util.List;
import com.xuning.video.domain.GoodsVideo;

/**
 * 视频信息Service接口
 * 
 * @author xuning
 * @date 2023-02-25
 */
public interface IGoodsVideoService 
{
    /**
     * 查询视频信息
     * 
     * @param videoId 视频信息主键
     * @return 视频信息
     */
    public GoodsVideo selectGoodsVideoByVideoId(Long videoId);

    /**
     * 查询视频信息列表
     * 
     * @param goodsVideo 视频信息
     * @return 视频信息集合
     */
    public List<GoodsVideo> selectGoodsVideoList(GoodsVideo goodsVideo);

    /**
     * 新增视频信息
     * 
     * @param goodsVideo 视频信息
     * @return 结果
     */
    public int insertGoodsVideo(GoodsVideo goodsVideo);

    /**
     * 修改视频信息
     * 
     * @param goodsVideo 视频信息
     * @return 结果
     */
    public int updateGoodsVideo(GoodsVideo goodsVideo);

    /**
     * 批量删除视频信息
     * 
     * @param videoIds 需要删除的视频信息主键集合
     * @return 结果
     */
    public int deleteGoodsVideoByVideoIds(Long[] videoIds);

    /**
     * 删除视频信息信息
     * 
     * @param videoId 视频信息主键
     * @return 结果
     */
    public int deleteGoodsVideoByVideoId(Long videoId);
}

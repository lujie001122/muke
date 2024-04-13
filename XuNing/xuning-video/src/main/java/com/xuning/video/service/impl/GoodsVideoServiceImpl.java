package com.xuning.video.service.impl;

import java.util.List;
import com.xuning.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xuning.video.mapper.GoodsVideoMapper;
import com.xuning.video.domain.GoodsVideo;
import com.xuning.video.service.IGoodsVideoService;

/**
 * 视频信息Service业务层处理
 * 
 * @author xuning
 * @date 2023-02-25
 */
@Service
public class GoodsVideoServiceImpl implements IGoodsVideoService 
{
    @Autowired
    private GoodsVideoMapper goodsVideoMapper;

    /**
     * 查询视频信息
     * 
     * @param videoId 视频信息主键
     * @return 视频信息
     */
    @Override
    public GoodsVideo selectGoodsVideoByVideoId(Long videoId)
    {
        return goodsVideoMapper.selectGoodsVideoByVideoId(videoId);
    }

    /**
     * 查询视频信息列表
     * 
     * @param goodsVideo 视频信息
     * @return 视频信息
     */
    @Override
    public List<GoodsVideo> selectGoodsVideoList(GoodsVideo goodsVideo)
    {
        return goodsVideoMapper.selectGoodsVideoList(goodsVideo);
    }

    /**
     * 新增视频信息
     * 
     * @param goodsVideo 视频信息
     * @return 结果
     */
    @Override
    public int insertGoodsVideo(GoodsVideo goodsVideo)
    {
        goodsVideo.setCreateTime(DateUtils.getNowDate());
        return goodsVideoMapper.insertGoodsVideo(goodsVideo);
    }

    /**
     * 修改视频信息
     * 
     * @param goodsVideo 视频信息
     * @return 结果
     */
    @Override
    public int updateGoodsVideo(GoodsVideo goodsVideo)
    {
        goodsVideo.setUpdateTime(DateUtils.getNowDate());
        return goodsVideoMapper.updateGoodsVideo(goodsVideo);
    }

    /**
     * 批量删除视频信息
     * 
     * @param videoIds 需要删除的视频信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsVideoByVideoIds(Long[] videoIds)
    {
        return goodsVideoMapper.deleteGoodsVideoByVideoIds(videoIds);
    }

    /**
     * 删除视频信息信息
     * 
     * @param videoId 视频信息主键
     * @return 结果
     */
    @Override
    public int deleteGoodsVideoByVideoId(Long videoId)
    {
        return goodsVideoMapper.deleteGoodsVideoByVideoId(videoId);
    }
}

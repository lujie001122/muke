package com.xuning.danmu.service;

import java.util.List;

import com.xuning.danmu.domain.VideoDanmu;

/**
 * 弹幕信息Service接口
 * 
 * @author xuning
 * @date 2023-02-26
 */
public interface IVideoDanmuService 
{
    /**
     * 查询弹幕信息
     * 
     * @param danmuId 弹幕信息主键
     * @return 弹幕信息
     */
    public VideoDanmu selectVideoDanmuByDanmuId(Long danmuId);

    /**
     * 查询弹幕信息列表
     * 
     * @param videoDanmu 弹幕信息
     * @return 弹幕信息集合
     */
    public List<VideoDanmu> selectVideoDanmuList(VideoDanmu videoDanmu);

    /**
     * 新增弹幕信息
     * 
     * @param videoDanmu 弹幕信息
     * @return 结果
     */
    public int insertVideoDanmu(VideoDanmu videoDanmu);

    /**
     * 修改弹幕信息
     * 
     * @param videoDanmu 弹幕信息
     * @return 结果
     */
    public int updateVideoDanmu(VideoDanmu videoDanmu);

    /**
     * 批量删除弹幕信息
     * 
     * @param danmuIds 需要删除的弹幕信息主键集合
     * @return 结果
     */
    public int deleteVideoDanmuByDanmuIds(Long[] danmuIds);

    /**
     * 删除弹幕信息信息
     * 
     * @param danmuId 弹幕信息主键
     * @return 结果
     */
    public int deleteVideoDanmuByDanmuId(Long danmuId);

    public List selectVideDamus(VideoDanmu videoDanmu);
}

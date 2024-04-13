package com.xuning.danmu.service.impl;

import java.util.List;

import com.xuning.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xuning.danmu.mapper.VideoDanmuMapper;
import com.xuning.danmu.domain.VideoDanmu;
import com.xuning.danmu.service.IVideoDanmuService;

/**
 * 弹幕信息Service业务层处理
 * 
 * @author xuning
 * @date 2023-02-26
 */
@Service
public class VideoDanmuServiceImpl implements IVideoDanmuService 
{
    @Autowired
    private VideoDanmuMapper videoDanmuMapper;

    /**
     * 查询弹幕信息
     * 
     * @param danmuId 弹幕信息主键
     * @return 弹幕信息
     */
    @Override
    public VideoDanmu selectVideoDanmuByDanmuId(Long danmuId)
    {
        return videoDanmuMapper.selectVideoDanmuByDanmuId(danmuId);
    }

    /**
     * 查询弹幕信息列表
     * 
     * @param videoDanmu 弹幕信息
     * @return 弹幕信息
     */
    @Override
    public List<VideoDanmu> selectVideoDanmuList(VideoDanmu videoDanmu)
    {
        return videoDanmuMapper.selectVideoDanmuList(videoDanmu);
    }

    /**
     * 新增弹幕信息
     * 
     * @param videoDanmu 弹幕信息
     * @return 结果
     */
    @Override
    public int insertVideoDanmu(VideoDanmu videoDanmu)
    {
        videoDanmu.setCreateTime(DateUtils.getNowDate());
        return videoDanmuMapper.insertVideoDanmu(videoDanmu);
    }

    /**
     * 修改弹幕信息
     * 
     * @param videoDanmu 弹幕信息
     * @return 结果
     */
    @Override
    public int updateVideoDanmu(VideoDanmu videoDanmu)
    {
        return videoDanmuMapper.updateVideoDanmu(videoDanmu);
    }

    /**
     * 批量删除弹幕信息
     * 
     * @param danmuIds 需要删除的弹幕信息主键
     * @return 结果
     */
    @Override
    public int deleteVideoDanmuByDanmuIds(Long[] danmuIds)
    {
        return videoDanmuMapper.deleteVideoDanmuByDanmuIds(danmuIds);
    }

    /**
     * 删除弹幕信息信息
     * 
     * @param danmuId 弹幕信息主键
     * @return 结果
     */
    @Override
    public int deleteVideoDanmuByDanmuId(Long danmuId)
    {
        return videoDanmuMapper.deleteVideoDanmuByDanmuId(danmuId);
    }

    @Override
    public List selectVideDamus(VideoDanmu videoDanmu){
        return videoDanmuMapper.selectVideDamus(videoDanmu);
    }
}

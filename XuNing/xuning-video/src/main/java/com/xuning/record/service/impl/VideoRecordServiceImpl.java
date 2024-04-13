package com.xuning.record.service.impl;

import java.util.List;
import com.xuning.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xuning.record.mapper.VideoRecordMapper;
import com.xuning.record.domain.VideoRecord;
import com.xuning.record.service.IVideoRecordService;

/**
 * 学习记录Service业务层处理
 * 
 * @author xuning
 * @date 2023-02-26
 */
@Service
public class VideoRecordServiceImpl implements IVideoRecordService 
{
    @Autowired
    private VideoRecordMapper videoRecordMapper;

    /**
     * 查询学习记录
     * 
     * @param recordId 学习记录主键
     * @return 学习记录
     */
    @Override
    public VideoRecord selectVideoRecordByRecordId(Long recordId)
    {
        return videoRecordMapper.selectVideoRecordByRecordId(recordId);
    }

    /**
     * 查询学习记录列表
     * 
     * @param videoRecord 学习记录
     * @return 学习记录
     */
    @Override
    public List<VideoRecord> selectVideoRecordList(VideoRecord videoRecord)
    {
        return videoRecordMapper.selectVideoRecordList(videoRecord);
    }

    /**
     * 新增学习记录
     * 
     * @param videoRecord 学习记录
     * @return 结果
     */
    @Override
    public int insertVideoRecord(VideoRecord videoRecord)
    {
        videoRecord.setCreateTime(DateUtils.getNowDate());
        return videoRecordMapper.insertVideoRecord(videoRecord);
    }

    /**
     * 修改学习记录
     * 
     * @param videoRecord 学习记录
     * @return 结果
     */
    @Override
    public int updateVideoRecord(VideoRecord videoRecord)
    {
        return videoRecordMapper.updateVideoRecord(videoRecord);
    }

    /**
     * 批量删除学习记录
     * 
     * @param recordIds 需要删除的学习记录主键
     * @return 结果
     */
    @Override
    public int deleteVideoRecordByRecordIds(Long[] recordIds)
    {
        return videoRecordMapper.deleteVideoRecordByRecordIds(recordIds);
    }

    /**
     * 删除学习记录信息
     * 
     * @param recordId 学习记录主键
     * @return 结果
     */
    @Override
    public int deleteVideoRecordByRecordId(Long recordId)
    {
        return videoRecordMapper.deleteVideoRecordByRecordId(recordId);
    }
}

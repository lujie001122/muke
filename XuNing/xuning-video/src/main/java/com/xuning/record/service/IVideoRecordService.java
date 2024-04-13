package com.xuning.record.service;

import java.util.List;
import com.xuning.record.domain.VideoRecord;

/**
 * 学习记录Service接口
 * 
 * @author xuning
 * @date 2023-02-26
 */
public interface IVideoRecordService 
{
    /**
     * 查询学习记录
     * 
     * @param recordId 学习记录主键
     * @return 学习记录
     */
    public VideoRecord selectVideoRecordByRecordId(Long recordId);

    /**
     * 查询学习记录列表
     * 
     * @param videoRecord 学习记录
     * @return 学习记录集合
     */
    public List<VideoRecord> selectVideoRecordList(VideoRecord videoRecord);

    /**
     * 新增学习记录
     * 
     * @param videoRecord 学习记录
     * @return 结果
     */
    public int insertVideoRecord(VideoRecord videoRecord);

    /**
     * 修改学习记录
     * 
     * @param videoRecord 学习记录
     * @return 结果
     */
    public int updateVideoRecord(VideoRecord videoRecord);

    /**
     * 批量删除学习记录
     * 
     * @param recordIds 需要删除的学习记录主键集合
     * @return 结果
     */
    public int deleteVideoRecordByRecordIds(Long[] recordIds);

    /**
     * 删除学习记录信息
     * 
     * @param recordId 学习记录主键
     * @return 结果
     */
    public int deleteVideoRecordByRecordId(Long recordId);
}

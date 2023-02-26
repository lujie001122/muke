package com.luke.record.mapper;

import java.util.List;
import com.luke.record.domain.VideoRecord;

/**
 * 学习记录Mapper接口
 * 
 * @author luke
 * @date 2023-02-26
 */
public interface VideoRecordMapper 
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
     * 删除学习记录
     * 
     * @param recordId 学习记录主键
     * @return 结果
     */
    public int deleteVideoRecordByRecordId(Long recordId);

    /**
     * 批量删除学习记录
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteVideoRecordByRecordIds(Long[] recordIds);
}

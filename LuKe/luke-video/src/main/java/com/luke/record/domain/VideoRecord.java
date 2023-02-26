package com.luke.record.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.luke.common.annotation.Excel;
import com.luke.common.core.domain.BaseEntity;

/**
 * 学习记录对象 video_record
 * 
 * @author luke
 * @date 2023-02-26
 */
public class VideoRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录Id */
    @Excel(name = "记录Id")
    private Long recordId;

    /** 视频id */
    @Excel(name = "视频id")
    private Long videoId;
    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;
    /** 视频类型 */
    @Excel(name = "视频类型")
    private String videoType;

    /** 视频简介 */
    @Excel(name = "视频简介")
    private String videoBrief;

    /** 视频详情 */
    @Excel(name = "视频详情")
    private String videoContent;

    /** 视频路径 */
    @Excel(name = "视频路径")
    private String videoPath;

    /** 视频封面 */
    @Excel(name = "视频封面")
    private String imgPath;

    /** 视频状态（0上架 1下架） */
    @Excel(name = "视频状态", readConverterExp = "0=上架,1=下架")
    private String status;

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoBrief() {
        return videoBrief;
    }

    public void setVideoBrief(String videoBrief) {
        this.videoBrief = videoBrief;
    }

    public String getVideoContent() {
        return videoContent;
    }

    public void setVideoContent(String videoContent) {
        this.videoContent = videoContent;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "VideoRecord{" +
                "recordId=" + recordId +
                ", videoId=" + videoId +
                ", userId=" + userId +
                ", videoType='" + videoType + '\'' +
                ", videoBrief='" + videoBrief + '\'' +
                ", videoContent='" + videoContent + '\'' +
                ", videoPath='" + videoPath + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

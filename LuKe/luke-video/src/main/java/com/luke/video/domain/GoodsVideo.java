package com.luke.video.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.luke.common.annotation.Excel;
import com.luke.common.core.domain.BaseEntity;

/**
 * 视频信息对象 goods_video
 * 
 * @author luke
 * @date 2023-02-25
 */
public class GoodsVideo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 视频id */
    private Long videoId;

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

    public void setVideoId(Long videoId) 
    {
        this.videoId = videoId;
    }

    public Long getVideoId() 
    {
        return videoId;
    }
    public void setVideoType(String videoType) 
    {
        this.videoType = videoType;
    }

    public String getVideoType() 
    {
        return videoType;
    }
    public void setVideoBrief(String videoBrief) 
    {
        this.videoBrief = videoBrief;
    }

    public String getVideoBrief() 
    {
        return videoBrief;
    }
    public void setVideoContent(String videoContent) 
    {
        this.videoContent = videoContent;
    }

    public String getVideoContent() 
    {
        return videoContent;
    }
    public void setVideoPath(String videoPath) 
    {
        this.videoPath = videoPath;
    }

    public String getVideoPath() 
    {
        return videoPath;
    }
    public void setImgPath(String imgPath) 
    {
        this.imgPath = imgPath;
    }

    public String getImgPath() 
    {
        return imgPath;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("videoId", getVideoId())
            .append("videoType", getVideoType())
            .append("videoBrief", getVideoBrief())
            .append("videoContent", getVideoContent())
            .append("videoPath", getVideoPath())
            .append("imgPath", getImgPath())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

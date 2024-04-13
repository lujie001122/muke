package com.xuning.danmu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.xuning.common.annotation.Excel;
import com.xuning.common.core.domain.BaseEntity;

/**
 * 弹幕信息对象 video_danmu
 * 
 * @author xuning
 * @date 2023-02-26
 */
public class VideoDanmu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 弹幕Id */
    private Long danmuId;

    /** 视频id */
    @Excel(name = "视频id")
    private Long videoId;

    /** 弹幕内容 */
    @Excel(name = "弹幕内容")
    private String text;
    /** 弹幕颜色 */
    @Excel(name = "弹幕颜色")
    private String color;
    /** 弹幕时间 */
    @Excel(name = "弹幕时间")
    private Long time;

    public void setDanmuId(Long danmuId) 
    {
        this.danmuId = danmuId;
    }

    public Long getDanmuId() 
    {
        return danmuId;
    }
    public void setVideoId(Long videoId) 
    {
        this.videoId = videoId;
    }

    public Long getVideoId() 
    {
        return videoId;
    }
    public void setText(String text) 
    {
        this.text = text;
    }

    public String getText() 
    {
        return text;
    }
    public void setTime(Long time) 
    {
        this.time = time;
    }

    public Long getTime() 
    {
        return time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("danmuId", getDanmuId())
            .append("videoId", getVideoId())
            .append("text", getText())
            .append("time", getTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}

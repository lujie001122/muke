package com.luke.record.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.luke.common.annotation.Log;
import com.luke.common.core.controller.BaseController;
import com.luke.common.core.domain.AjaxResult;
import com.luke.common.enums.BusinessType;
import com.luke.record.domain.VideoRecord;
import com.luke.record.service.IVideoRecordService;
import com.luke.common.utils.poi.ExcelUtil;
import com.luke.common.core.page.TableDataInfo;

/**
 * 学习记录Controller
 * 
 * @author luke
 * @date 2023-02-26
 */
@RestController
@RequestMapping("/video/record")
public class VideoRecordController extends BaseController
{
    @Autowired
    private IVideoRecordService videoRecordService;

    /**
     * 查询学习记录列表
     */
    @PreAuthorize("@ss.hasPermi('video:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoRecord videoRecord)
    {
        startPage();
        Long userId = getUserId();
        videoRecord.setUserId(userId);
        List<VideoRecord> list = videoRecordService.selectVideoRecordList(videoRecord);
        return getDataTable(list);
    }

    /**
     * 导出学习记录列表
     */
    @PreAuthorize("@ss.hasPermi('video:record:export')")
    @Log(title = "学习记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VideoRecord videoRecord)
    {
        List<VideoRecord> list = videoRecordService.selectVideoRecordList(videoRecord);
        ExcelUtil<VideoRecord> util = new ExcelUtil<VideoRecord>(VideoRecord.class);
        util.exportExcel(response, list, "学习记录数据");
    }

    /**
     * 获取学习记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(videoRecordService.selectVideoRecordByRecordId(recordId));
    }

    /**
     * 新增学习记录
     */
    @PreAuthorize("@ss.hasPermi('video:record:add')")
    @Log(title = "学习记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VideoRecord videoRecord)
    {
        Long userId = getUserId();
        videoRecord.setUserId(userId);
        List<VideoRecord> list= videoRecordService.selectVideoRecordList(videoRecord);
        if (list.size()>0)return toAjax(true);
        return toAjax(videoRecordService.insertVideoRecord(videoRecord));
    }

    /**
     * 修改学习记录
     */
    @PreAuthorize("@ss.hasPermi('video:record:edit')")
    @Log(title = "学习记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VideoRecord videoRecord)
    {
        return toAjax(videoRecordService.updateVideoRecord(videoRecord));
    }

    /**
     * 删除学习记录
     */
    @PreAuthorize("@ss.hasPermi('video:record:remove')")
    @Log(title = "学习记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(videoRecordService.deleteVideoRecordByRecordIds(recordIds));
    }
}

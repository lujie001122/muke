package com.luke.video.controller;

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
import com.luke.video.domain.GoodsVideo;
import com.luke.video.service.IGoodsVideoService;
import com.luke.common.utils.poi.ExcelUtil;
import com.luke.common.core.page.TableDataInfo;

/**
 * 视频信息Controller
 * 
 * @author luke
 * @date 2023-02-25
 */
@RestController
@RequestMapping("/video/video")
public class GoodsVideoController extends BaseController
{
    @Autowired
    private IGoodsVideoService goodsVideoService;

    /**
     * 查询视频信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:video:list')")
    @GetMapping("/list")
    public TableDataInfo list(GoodsVideo goodsVideo)
    {
        startPage();
        List<GoodsVideo> list = goodsVideoService.selectGoodsVideoList(goodsVideo);
        return getDataTable(list);
    }

    /**
     * 导出视频信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:video:export')")
    @Log(title = "视频信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GoodsVideo goodsVideo)
    {
        List<GoodsVideo> list = goodsVideoService.selectGoodsVideoList(goodsVideo);
        ExcelUtil<GoodsVideo> util = new ExcelUtil<GoodsVideo>(GoodsVideo.class);
        util.exportExcel(response, list, "视频信息数据");
    }

    /**
     * 获取视频信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:video:query')")
    @GetMapping(value = "/{videoId}")
    public AjaxResult getInfo(@PathVariable("videoId") Long videoId)
    {
        return success(goodsVideoService.selectGoodsVideoByVideoId(videoId));
    }

    /**
     * 新增视频信息
     */
    @PreAuthorize("@ss.hasPermi('video:video:add')")
    @Log(title = "视频信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GoodsVideo goodsVideo)
    {
        return toAjax(goodsVideoService.insertGoodsVideo(goodsVideo));
    }

    /**
     * 修改视频信息
     */
    @PreAuthorize("@ss.hasPermi('video:video:edit')")
    @Log(title = "视频信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GoodsVideo goodsVideo)
    {
        return toAjax(goodsVideoService.updateGoodsVideo(goodsVideo));
    }

    /**
     * 删除视频信息
     */
    @PreAuthorize("@ss.hasPermi('video:video:remove')")
    @Log(title = "视频信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{videoIds}")
    public AjaxResult remove(@PathVariable Long[] videoIds)
    {
        return toAjax(goodsVideoService.deleteGoodsVideoByVideoIds(videoIds));
    }
}

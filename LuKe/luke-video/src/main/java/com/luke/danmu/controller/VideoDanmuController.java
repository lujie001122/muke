package com.luke.danmu.controller;

import java.util.List;
import java.util.Map;
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
import com.luke.danmu.domain.VideoDanmu;
import com.luke.danmu.service.IVideoDanmuService;
import com.luke.common.utils.poi.ExcelUtil;
import com.luke.common.core.page.TableDataInfo;

/**
 * 弹幕信息Controller
 * 
 * @author luke
 * @date 2023-02-26
 */
@RestController
@RequestMapping("/video/danmu")
public class VideoDanmuController extends BaseController
{
    @Autowired
    private IVideoDanmuService videoDanmuService;

    /**
     * 查询弹幕信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:danmu:list')")
    @GetMapping("/list")
    public TableDataInfo list(VideoDanmu videoDanmu)
    {
        startPage();
        List<VideoDanmu> list = videoDanmuService.selectVideoDanmuList(videoDanmu);
        return getDataTable(list);
    }
    @PreAuthorize("@ss.hasPermi('video:danmu:list')")
    @GetMapping("/damus")
    public List selectVideDamus(VideoDanmu videoDanmu){
        List list = videoDanmuService.selectVideDamus(videoDanmu);
        return list;
    }
    /**
     * 导出弹幕信息列表
     */
    @PreAuthorize("@ss.hasPermi('video:danmu:export')")
    @Log(title = "弹幕信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, VideoDanmu videoDanmu)
    {
        List<VideoDanmu> list = videoDanmuService.selectVideoDanmuList(videoDanmu);
        ExcelUtil<VideoDanmu> util = new ExcelUtil<VideoDanmu>(VideoDanmu.class);
        util.exportExcel(response, list, "弹幕信息数据");
    }

    /**
     * 获取弹幕信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('video:danmu:query')")
    @GetMapping(value = "/{danmuId}")
    public AjaxResult getInfo(@PathVariable("danmuId") Long danmuId)
    {
        return success(videoDanmuService.selectVideoDanmuByDanmuId(danmuId));
    }

    /**
     * 新增弹幕信息
     */
    @PreAuthorize("@ss.hasPermi('video:danmu:add')")
    @Log(title = "弹幕信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VideoDanmu videoDanmu)
    {
        return toAjax(videoDanmuService.insertVideoDanmu(videoDanmu));
    }

    /**
     * 修改弹幕信息
     */
    @PreAuthorize("@ss.hasPermi('video:danmu:edit')")
    @Log(title = "弹幕信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VideoDanmu videoDanmu)
    {
        return toAjax(videoDanmuService.updateVideoDanmu(videoDanmu));
    }

    /**
     * 删除弹幕信息
     */
    @PreAuthorize("@ss.hasPermi('video:danmu:remove')")
    @Log(title = "弹幕信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{danmuIds}")
    public AjaxResult remove(@PathVariable Long[] danmuIds)
    {
        return toAjax(videoDanmuService.deleteVideoDanmuByDanmuIds(danmuIds));
    }
}

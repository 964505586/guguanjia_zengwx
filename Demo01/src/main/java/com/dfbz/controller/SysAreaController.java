package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.SysArea;
import com.dfbz.service.SysAreaService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysAreaController
 * @Description:
 * @author: zwx
 * @Date: 2020/1/4 14:42
 * @version: V1.0
 */
@RestController
@RequestMapping("manager/area")
public class SysAreaController {

    @Autowired
    SysAreaService sysAreaService;

    /**
     * 跳转到查询页
     * @return
     */
    @RequestMapping("")
    public ModelAndView toIndex() {
        return new ModelAndView("/area/area");
    }

    /**
     * Excel下载操作:
     * 1.设置响应头
     * 2.设置文件乱码处理
     * 3.获取响应数据流
     * 4.写出到页面
     */
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Disposition", "attachment;filename=sysArea.xls");
        ServletOutputStream outputStream = response.getOutputStream();
        sysAreaService.writeExcel(outputStream);
    }

    @RequestMapping("upload")
    public Result upload(MultipartFile upFile) throws IOException {
        Result result = new Result();
        int i = sysAreaService.readExcel(upFile.getInputStream());
        if (i > 0) {
            result.setMsg("导入数据成功");
            result.setSuccess(true);
        }
        return result;
    }

    @RequestMapping("selectPage")
    public PageInfo<SysArea> selectPage(@RequestBody Map<String, Object> map) {
        return sysAreaService.selectByCondition(map);
    }

    @RequestMapping("selectAll")
    public List<SysArea> selectAll() {
        return sysAreaService.selectAll();
    }

    @RequestMapping("toUpdate")
    public SysArea toUpdate(long id) {
        return sysAreaService.selectAid(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/area/save");
    }

    @RequestMapping("awesome")
    public ModelAndView awesome() {
        return new ModelAndView("/modules/font-awesome");
    }

    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody SysArea sysArea) {
        Result result = new Result();
        int i = sysAreaService.updateArea(sysArea);
        if (i > 0) {
            result.setMsg("更新成功");
            result.setSuccess(true);
        }
        return result;
    }


}

package com.dfbz.controller;

import com.dfbz.entity.AppVersion;
import com.dfbz.entity.Result;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;

@RestController
@RequestMapping("manager/app")
public class AppVersionController {

    @Autowired
    AppVersionService appVersionService;

    /**
     * RestController返回视图到默认视图解析器处理通过ModelAndView返回默认的视图解析器
     * @return
     */
    @RequestMapping("index")
    public ModelAndView toIndex() {
        return new ModelAndView("/app/index");
    }

    @RequestMapping("toList")
    public PageInfo<AppVersion> toList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {
        PageInfo<AppVersion> appVersionPageInfo = appVersionService.selectPage(pageNum, pageSize);
        System.out.println(appVersionPageInfo);
        return appVersionPageInfo;
    }

    /**
     * 到更新页面的查找app对象功能
     * @param id
     * @return
     */
    @RequestMapping("toUpdate")
    public AppVersion toUpdate(Integer id) {
        return appVersionService.selectByPrimaryKey(id);
    }

    /**
     * 跳转到更新页面
     * @return
     */
    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/app/update");
    }

    /**
     * 更新app功能
     * @param appVersion
     * @return
     */
    @RequestMapping("doUpdate")
    public Result doUpdate(@RequestBody AppVersion appVersion) {
        appVersion.setUpdateDate(new Date());
        Result result = new Result();
        int i = appVersionService.updateByPrimaryKeySelective(appVersion);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("更新成功!");
        }
        return result;
    }

    @RequestMapping("insert")
    public Result insert(@RequestBody AppVersion appVersion) {
        appVersion.setCreateDate(new Date());
        appVersion.setUpdateDate(new Date());
        appVersion.setDelFlag("0");
        Result result = new Result();
        int i = appVersionService.insertSelective(appVersion);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("添加成功!");
        }
        return result;
    }

}

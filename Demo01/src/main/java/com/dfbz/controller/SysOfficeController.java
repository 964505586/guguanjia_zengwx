package com.dfbz.controller;

import com.dfbz.entity.SysArea;
import com.dfbz.entity.SysOffice;
import com.dfbz.service.SysOfficeService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysOfficeController
 * @Description:
 * @author: zwx
 * @Date: 2019/12/27 0:26
 * @version: V1.0
 */
@RestController
@RequestMapping("manager/sysOffice")
public class SysOfficeController {

    @Autowired
    SysOfficeService sysOfficeService;

    @RequestMapping("list")
    public List<SysOffice> selectAll() {
        return sysOfficeService.selectAll();
    }

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("/office/office");
    }

    @RequestMapping("selectPage")
    public PageInfo<SysOffice> selectPage(@RequestBody Map<String, Object> map) {
        return sysOfficeService.selectByCondition(map);
    }

    @RequestMapping("toUpdate")
    public SysArea toUpdate(long id) {
        return sysOfficeService.selectAid(id);
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/office/update");
    }
}

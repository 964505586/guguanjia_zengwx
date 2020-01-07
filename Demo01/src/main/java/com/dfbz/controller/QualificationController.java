package com.dfbz.controller;

import com.dfbz.entity.Qualification;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("manager/qualification")
public class QualificationController {

    @Autowired
    QualificationService qualificationService;

    /**
     * 跳转到资质审核模块主列表显示页面
     * @return
     */
    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/qualification/index");
    }

    @RequestMapping("toList")
    public PageInfo<Qualification> toList(@RequestBody Map<String, Object> map) {
        PageInfo<Qualification> qualificationPageInfo = qualificationService.selectByCondition(map);
        System.out.println(qualificationPageInfo);
        return qualificationPageInfo;
    }

    /**
     * 到更新页面的查找app对象功能
     * @param id
     * @return
     */
    @RequestMapping("toUpdate")
    public Map<String, Object> toUpdate(Integer id) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("qualification", qualificationService.selectByPrimaryKey(id));
        map.put("oid", qualificationService.selectOfficeId(id));
        return map;
    }

    /**
     * 跳转到更新页面
     * @return
     */
    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/qualification/update");
    }










}

package com.dfbz.controller;

import com.dfbz.entity.Examine;
import com.dfbz.service.ExamineService;
import com.dfbz.service.impl.ExamineServiceImpl;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ExamineController
 * @Description:
 * @author: zwx
 * @Date: 2019/12/27 0:23
 * @version: V1.0
 */
@RestController
@RequestMapping("manager/examine")
public class ExamineController {

    @Autowired
    ExamineService examineService;

    @RequestMapping("index")
    public ModelAndView index() {
        return new ModelAndView("/examine/index");
    }

    @RequestMapping("toList")
    public PageInfo<Examine> toList(@RequestBody Map<String, Object> map) {
        Set<String> strings = map.keySet();
        System.out.println("-----------------------------------------------------------------------------------------------");
        for (String str : strings) {
            System.out.println(str + "  :  " + map.get(str));
        }
        return examineService.selectByCondition(map);
    }

            @RequestMapping("toUpdate")
    @ResponseBody
    public Examine toUpdate(long id) {
        return examineService.selectByPrimaryKey(id);
    }
}

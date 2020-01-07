package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.Statute;
import com.dfbz.service.StatuteService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

// 替代Controller   自动添加@ResponseBody转换
@RestController
@RequestMapping("manager/statute")
public class StatuteController {

    @Autowired
    StatuteService statuteService;

    @RequestMapping("index")
    public ModelAndView toIndex() {
        return new ModelAndView("/statute/index");
    }

    @RequestMapping("toUpdatePage")
    public ModelAndView toUpdatePage() {
        return new ModelAndView("/statute/update");
    }

    @RequestMapping("toList")
    public PageInfo<Statute> selectByCondition(@RequestBody Map<String, Object> map) {
        return statuteService.selectByCondition(map);
    }

    @RequestMapping("toUpdate")
    public Statute toUpdate(Long id) {
        return statuteService.selectByPrimaryKey(id);
    }

    @RequestMapping("update")
    public Result update(Statute statute) {
        int i = statuteService.updateByPrimaryKeySelective(statute);
        Result result = new Result();
        if (i > 0) {
            result.setMsg("操作成功");
            result.setSuccess(true);
        }
        return result;
    }

}

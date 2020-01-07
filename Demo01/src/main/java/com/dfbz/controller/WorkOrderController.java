package com.dfbz.controller;

import com.dfbz.entity.WorkOrder;
import com.dfbz.service.WorkOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;

/**
 * @ClassName: WorkOrderController
 * @Description:
 * @author: zwx
 * @Date: 2019/12/29 19:09
 * @version: V1.0
 */
@RestController
@RequestMapping("manager/admin/work")
public class WorkOrderController {


    @Autowired
    WorkOrderService workOrderService;

    @RequestMapping("")
    public ModelAndView index() {
        return new ModelAndView("/work/admin/work");
    }

    @RequestMapping("toList")
    public PageInfo<WorkOrder> toList(@RequestBody Map<String, Object> map) {
        return workOrderService.selectByCondition(map);
    }

    @RequestMapping("toUpdate")
    @ResponseBody
    public WorkOrder toUpdate(long id) {
        return workOrderService.selectByPrimaryKey(id);
    }

    @RequestMapping("selectByOid")
    public Map<String, Object> selectByOid(long oid) {
        return workOrderService.selectByOid(oid);
    }

    @RequestMapping("toDetail")
    public ModelAndView toDetail() {
        System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz");
        return new ModelAndView("/work/work-detail");
    }
}

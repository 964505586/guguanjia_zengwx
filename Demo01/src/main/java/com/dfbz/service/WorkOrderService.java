package com.dfbz.service;

import com.dfbz.entity.WorkOrder;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WorkOrderService
 * @Description:
 * @author: zwx
 * @Date: 2019/12/29 19:10
 * @version: V1.0
 */
public interface WorkOrderService extends IService<WorkOrder> {

    PageInfo<WorkOrder> selectByCondition(Map<String, Object> map);

    // 根据workOrder的id查询一个详单信息
    Map<String, Object> selectByOid(long oid);
}

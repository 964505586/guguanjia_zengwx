package com.dfbz.mapper;

import com.dfbz.entity.WorkOrder;
import com.dfbz.mapper.provider.WorkOrderProvider;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface WorkOrderMapper extends Mapper<WorkOrder> {

    @SelectProvider(type = WorkOrderProvider.class, method = "selectByCondition")
    List<WorkOrder> selectByCondition(Map<String, Object> map);
}
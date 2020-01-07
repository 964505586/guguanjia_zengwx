package com.dfbz.service.impl;

import com.dfbz.entity.*;
import com.dfbz.mapper.DetailMapper;
import com.dfbz.mapper.SysUserMapper;
import com.dfbz.mapper.TransferMapper;
import com.dfbz.mapper.WorkOrderMapper;
import com.dfbz.service.WorkOrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: WorkOrderServiceImpl
 * @Description:
 * @author: zwx
 * @Date: 2019/12/29 19:10
 * @version: V1.0
 */
@Service
@Transactional
public class WorkOrderServiceImpl extends ServiceImpl<WorkOrder> implements WorkOrderService {

    @Autowired
    WorkOrderMapper workOrderMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    DetailMapper detailMapper;
    @Autowired
    TransferMapper transferMapper;

    @Override
    public PageInfo<WorkOrder> selectByCondition(Map<String, Object> map) {
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 5);
        }

        PageHelper.startPage((Integer) map.get("pageNum"), (Integer)map.get("pageSize"));
        List<WorkOrder> list = workOrderMapper.selectByCondition(map);
        return new PageInfo<>(list);
    }

    /**
     * 1.根据oid查询 workOrder信息
     * 2.根据oid查询创建、运输、处理用户
     * 3.根据oid查询详单
     * 4.根据oid查询转运记录
     * @param oid
     * @return
     */
    @Override
    public Map<String, Object> selectByOid(long oid) {
        WorkOrder workOrder = workOrderMapper.selectByPrimaryKey(oid);
        SysUser createUser = sysUserMapper.selectById(workOrder.getCreateUserId());
        SysUser transportUser = null;
        if (!StringUtils.isEmpty(workOrder.getTransportUserId())) {
            transportUser = sysUserMapper.selectById(workOrder.getTransportUserId());
        }
        SysUser recipientUser = null;
        if (!StringUtils.isEmpty(workOrder.getRecipientUserId())) {
            recipientUser = sysUserMapper.selectById(workOrder.getRecipientUserId());
        }
        List<Detail> details = detailMapper.selectByOid(oid);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        for (Detail dt : details) {
            System.out.println(dt.toString());
        }
        List<Transfer> transfers = transferMapper.selectByOid(oid);
        HashMap<String, Object> map = new HashMap<>();
        map.put("work", workOrder);
        map.put("createUser", createUser);
        map.put("transportUser", transportUser);
        map.put("recipientUser", recipientUser);
        map.put("details", details);
        map.put("transfers", transfers);
        return map;
    }
}

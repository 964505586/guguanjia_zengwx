package com.dfbz.service.impl;

import com.dfbz.entity.Examine;
import com.dfbz.mapper.ExamineMapper;
import com.dfbz.service.ExamineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExamineServiceImpl
 * @Description:
 * @author: zwx
 * @Date: 2019/12/27 0:22
 * @version: V1.0
 */
@Service
@Transactional
public class ExamineServiceImpl extends ServiceImpl<Examine> implements ExamineService {

    @Autowired
    ExamineMapper examineMapper;

    @Override
    public PageInfo<Examine> selectByCondition(Map<String, Object> map) {
        // 默认值设置
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 5);
        }
        PageHelper.startPage((Integer)map.get("pageNum"), (Integer) map.get("pageSize"));
        List<Examine> examines = examineMapper.selectByCondition(map);
        return new PageInfo<>(examines);
    }



}

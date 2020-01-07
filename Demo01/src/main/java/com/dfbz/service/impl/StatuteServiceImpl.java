package com.dfbz.service.impl;

import com.dfbz.entity.Statute;
import com.dfbz.mapper.StatuteMapper;
import com.dfbz.service.StatuteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: StatuteServiceImpl
 * @Description:
 * @author: zwx
 * @Date: 2020/1/1 2:51
 * @version: V1.0
 */
@Service
@Transactional
public class StatuteServiceImpl extends ServiceImpl<Statute> implements StatuteService {

    @Autowired
    StatuteMapper statuteMapper;

    @Override
    public PageInfo<Statute> selectByCondition(Map<String, Object> map) {
        // 默认值设置
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 4);
        }
        PageHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        List<Statute> statutes = statuteMapper.SelectByCondition(map);
        System.out.println("QQQQQQQQQQQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        for (Statute s : statutes) {
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            System.out.println(s);
        }
        return new PageInfo<>(statutes);
    }

}

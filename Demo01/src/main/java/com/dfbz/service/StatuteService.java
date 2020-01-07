package com.dfbz.service;

import com.dfbz.entity.Statute;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @ClassName: StatuteService
 * @Description:
 * @author: zwx
 * @Date: 2020/1/1 2:50
 * @version: V1.0
 */

public interface StatuteService extends IService<Statute> {

    PageInfo<Statute> selectByCondition(Map<String, Object> map);
}

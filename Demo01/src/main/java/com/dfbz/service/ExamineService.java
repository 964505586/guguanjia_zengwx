package com.dfbz.service;

import com.dfbz.entity.Examine;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExamineService
 * @Description:
 * @author: zwx
 * @Date: 2019/12/27 0:22
 * @version: V1.0
 */
public interface ExamineService extends IService<Examine> {

    PageInfo<Examine> selectByCondition(Map<String, Object> map);
}

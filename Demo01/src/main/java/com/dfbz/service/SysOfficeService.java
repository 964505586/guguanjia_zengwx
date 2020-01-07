package com.dfbz.service;

import com.dfbz.entity.SysArea;
import com.dfbz.entity.SysOffice;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @ClassName: SysOfficeService
 * @Description:
 * @author: zwx
 * @Date: 2019/12/27 0:24
 * @version: V1.0
 */
public interface SysOfficeService extends IService<SysOffice> {

    PageInfo<SysOffice> selectByCondition(Map<String, Object> map);

    SysArea selectAid(long id);



}

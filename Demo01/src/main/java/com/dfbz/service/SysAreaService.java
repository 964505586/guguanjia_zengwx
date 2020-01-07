package com.dfbz.service;

import com.dfbz.entity.SysArea;
import com.github.pagehelper.PageInfo;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

/**
 * @ClassName: SysAreaService
 * @Description:
 * @author: zwx
 * @Date: 2020/1/4 14:44
 * @version: V1.0
 */
public interface SysAreaService extends IService<SysArea> {

    OutputStream writeExcel(OutputStream outputStream);

    int readExcel(InputStream inputStream);

    PageInfo<SysArea> selectByCondition(Map<String, Object> map);

    SysArea selectAid(long id);

    int updateArea(SysArea sysArea);
}

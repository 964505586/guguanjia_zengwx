package com.dfbz.mapper;

import com.dfbz.entity.SysArea;
import com.dfbz.entity.SysOffice;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysOfficeMapper
 * @Description:
 * @author: zwx
 * @Date: 2019/12/27 0:20
 * @version: V1.0
 */

public interface SysOfficeMapper extends Mapper<SysOffice> {

    @SelectProvider(type=SelectProvider.class, method = "selectByCondition")
    List<SysOffice> selectByCondition(Map<String, Object> map);



}

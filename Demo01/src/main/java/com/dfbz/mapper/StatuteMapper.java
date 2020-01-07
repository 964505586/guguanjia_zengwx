package com.dfbz.mapper;

import com.dfbz.entity.Statute;
import com.dfbz.mapper.provider.StatuteSqlProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface StatuteMapper extends Mapper<Statute> {

    /**
     * 动态sql实现
     * @param
     * @return
     */
    @SelectProvider(type = StatuteSqlProvider.class, method = "SelectByCondition")
    List<Statute> SelectByCondition(Map<String, Object> map);
}
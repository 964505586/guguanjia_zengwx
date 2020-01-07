package com.dfbz.mapper;

import com.dfbz.entity.Examine;
import com.dfbz.mapper.provider.ExamineProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.spring.annotation.MapperScan;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExamineMapper
 * @Description:
 * @author: zwx
 * @Date: 2019/12/27 0:20
 * @version: V1.0
 */
public interface ExamineMapper extends Mapper<Examine> {

    @SelectProvider(type = ExamineProvider.class, method = "selectByCondition")
    List<Examine> selectByCondition(Map<String, Object> map);

}

package com.dfbz.mapper;

import com.dfbz.entity.SysArea;
import com.dfbz.mapper.provider.SysAreaProvider;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;

public interface SysAreaMapper extends Mapper<SysArea> {

    @InsertProvider(type = SysAreaProvider.class, method = "insertBath")
    int insertBath(@Param("sysAreas") List<SysArea> sysAreas);

    /**
     * 根据父区域id查找所有区域
     * @return
     */
    @Select("select sub.id, sub.name, sub.code, parent.name parentName, sub.type from sys_area sub, sys_area parent" +
            " where sub.parent_id = parent.id and sub.parent_ids like CONCAT('%', #{id}, '%')")
    List<SysArea> selectByCondition(long id);

    @Select("select sub.*, parent.name parentName from sys_area sub, sys_area parent" +
            " where sub.parent_id = parent.id and sub.id = #{id}")
    SysArea selectAid(long id);

    /**
     * 根据父区域更新所有的子区域的parentIds
     * @param sysArea
     * @return
     */
    @Update("update sys_area set parent_ids = replace(parent_ids, #{oldParentIds}, #{parentIds}) where parent_ids like concat('%,', #{id}, ',%')")
    int updateParentIds(SysArea sysArea);


}
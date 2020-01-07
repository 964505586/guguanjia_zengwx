package com.dfbz.mapper;

import com.dfbz.entity.SysUser;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ClassName: SysUserMapper
 * @Description:
 * @author: zwx
 * @Date: 2019/12/29 19:43
 * @version: V1.0
 */
public interface SysUserMapper extends Mapper<SysUser> {

    /**
     * 根据用户id查询 用户信息和公司信息
     */
    @Select("select su.*, so.name officeName, so.id officeId from sys_user su, sys_office so" +
            " where su.id = #{uid} and su.office_id = so.id")
    @Results({
            @Result(property = "id", column = "su.id"),
            @Result(property = "sysOffice.id", column = "officeId"),
            @Result(property = "sysOffice.name", column = "officeName"),
    })
    SysUser selectById(long uid);
}

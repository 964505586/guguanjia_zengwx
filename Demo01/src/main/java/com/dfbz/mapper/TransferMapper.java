package com.dfbz.mapper;

import com.dfbz.entity.Transfer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TransferMapper extends Mapper<Transfer> {

    @Select("select tr.*, su.name, su.phone from transfer tr, sys_user su where tr.work_order_id = #{oid}" +
            " and tr.del_flag = 0 and tr.oprate_user_id = su.id order by tr.create_date desc")
    List<Transfer> selectByOid(long oid);
}
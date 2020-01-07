package com.dfbz.mapper.provider;

import com.alibaba.excel.util.StringUtils;
import com.dfbz.entity.SysArea;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: SysAreaProvider
 * @Description:
 * @author: zwx
 * @Date: 2020/1/4 19:55
 * @version: V1.0
 */
public class SysAreaProvider {

    /**
     * 批量插入
     */
    public String insertBath(@Param("sysAreas") List<SysArea> sysAreas) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `guguanjia`.`sys_area`(`parent_id`, `parent_ids`, `code`, `name`, `type`, `create_by`," +
                " `create_date`, `update_by`, `update_date`, `remarks`, `del_flag`, `icon`) VALUES");
        for (int i = 0; i < sysAreas.size(); i++) {
            sb.append("(");
            sb.append("#{sysAreas["+i+"].parentId}, #{sysAreas["+i+"].parentIds}, #{sysAreas["+i+"].code}, #{sysAreas["+i+"].name}," +
                    " #{sysAreas["+i+"].type}, #{sysAreas["+i+"].createBy}, #{sysAreas["+i+"].createDate}, #{sysAreas["+i+"].updateBy}," +
                    " #{sysAreas["+i+"].updateDate}, #{sysAreas["+i+"].remarks}, #{sysAreas["+i+"].delFlag}, #{sysAreas["+i+"].icon}");
            sb.append("),");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

//    public String selectByCondition(Map<String, Object> map) {
//        StringBuilder sb = new StringBuilder();
//        sb.append("select sa.id, sa.parent_id, sa.parent_ids, sa.code, sa.name, sa.type, sa.create_by," +
//                " sa.create_date, sa.update_by, sa.update_date, sa.remarks, sa.del_flag, sa.icon," +
//                " su.name parentName FROM sys_area AS sa LEFT JOIN sys_area su on sa.parent_id = su.id where sa.del_flag = 0");
//        if (!StringUtils.isEmpty(map.get("parentName"))) {
//            sb.append(" and sa.name like CONCAT('%', #{parentName}, '%')");
//        }
//        return sb.toString();
//    }


}

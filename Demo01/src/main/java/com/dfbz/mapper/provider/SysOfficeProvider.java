package com.dfbz.mapper.provider;

import org.springframework.util.StringUtils;
import java.util.Map;

public class SysOfficeProvider {

    public String selectByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("select so.*, sa.name areaName from sys_office so, sys_area sa where so.del_flag = 0 and so.area_id = sa.id");
        if (map.containsKey("name") && !StringUtils.isEmpty(map.get("name"))) {
            sb.append("and so.name like concat('%', #{name}, '%')");
        }
        return sb.toString();
    }



}

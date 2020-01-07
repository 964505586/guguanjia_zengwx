package com.dfbz.mapper.provider;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @ClassName: ExamineProvider
 * @Description:
 * @author: zwx
 * @Date: 2019/12/27 0:31
 * @version: V1.0
 */
public class ExamineProvider {

    public String selectByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("select ex.*, su.name userName, so.name officeName from examine ex, sys_user su, sys_office so" +
                " where ex.del_flag = 0 and ex.examine_user_id = su.id and su.office_id = so.id");
        if (map.containsKey("officeId") && !StringUtils.isEmpty(map.get("officeId"))) {
            sb.append(" and so.id = #{officeId}");
        }
        if (map.containsKey("userName") && !StringUtils.isEmpty(map.get("userName"))) {
            sb.append(" and su.name like concat('%'， #{userName}, '%')");
        }
        if (map.containsKey("type") && !StringUtils.isEmpty(map.get("type"))) {
            sb.append(" and ex.type = #{type}");
        }
        return sb.toString();
    }
}

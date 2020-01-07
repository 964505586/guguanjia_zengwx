package com.dfbz.mapper.provider;

import org.springframework.util.StringUtils;
import java.util.Map;

/**
 * @ClassName: StatuteSqlProvider
 * @Description:
 * @author: zwx
 * @Date: 2020/1/1 2:49
 * @version: V1.0
 */
public class StatuteSqlProvider {

    public String SelectByCondition(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from statute s where s.del_flag = 0");
        if (!StringUtils.isEmpty(map.get("type"))) {
            sb.append(" and s.type = #{type}");
        }
        return sb.toString();
    }

}

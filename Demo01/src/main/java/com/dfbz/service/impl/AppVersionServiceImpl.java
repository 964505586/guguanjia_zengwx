package com.dfbz.service.impl;

import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class AppVersionServiceImpl extends ServiceImpl<AppVersion> implements AppVersionService {

    public PageInfo<AppVersion> selectPage(int pageNum, int pageSize) {
        //开启分页拦截  分页插件会自动在最近一个sql执行前，自动添加分页的sql代码 limit x,x
        PageHelper.startPage(pageNum, pageSize);
        // 当前方法返回值已经被替换成Page对象类型
        List<AppVersion> appVersions = mapper.selectAll();
        return new PageInfo<>(appVersions);
    }

}

package com.dfbz.service;

import com.dfbz.entity.AppVersion;
import com.github.pagehelper.PageInfo;

public interface AppVersionService extends IService<AppVersion> {

    PageInfo<AppVersion> selectPage(int pageNum, int pageSize);
}

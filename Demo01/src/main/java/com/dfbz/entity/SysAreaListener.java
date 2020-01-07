package com.dfbz.entity;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.dfbz.mapper.SysAreaMapper;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: SysAreaListener
 * @Description:
 * @author: zwx
 * @Date: 2020/1/4 21:33
 * @version: V1.0
 */
public class SysAreaListener extends AnalysisEventListener<SysArea> {

    private SysAreaMapper sysAreaMapper;
    private List<SysArea> list = new ArrayList<>();

    public SysAreaListener() {
    }

    public SysAreaListener(SysAreaMapper sysAreaMapper) {
        this.sysAreaMapper = sysAreaMapper;
    }

    @Override
    public void invoke(SysArea sysArea, AnalysisContext analysisContext) {
        list.add(sysArea);
        if (list.size() == 10) {
            sysAreaMapper.insertBath(list);
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (list.size() > 0) {
            sysAreaMapper.insertBath(list);
        }
    }
}

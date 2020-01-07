package com.dfbz.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dfbz.entity.SysArea;
import com.dfbz.entity.SysAreaListener;
import com.dfbz.mapper.SysAreaMapper;
import com.dfbz.service.SysAreaService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: SysAreaServiceImpl
 * @Description:
 * @author: zwx
 * @Date: 2020/1/4 14:44
 * @version: V1.0
 */
@Service
@Transactional
public class SysAreaServiceImpl extends ServiceImpl<SysArea> implements SysAreaService {

    @Autowired
    SysAreaMapper sysAreaMapper;

    /**
     * 根据传入的输出流对象，进行excel写入操作，并返回该输出流对象
     * @param outputStream
     * @return
     */
    @Override
    public OutputStream writeExcel(OutputStream outputStream) {
        // 获取excel写出对象
        ExcelWriter excel = EasyExcel.write(outputStream, SysArea.class).build();
        // 获取sheet对象
        WriteSheet sheet = EasyExcel.writerSheet("sysarea数据").build();
        List<SysArea> sysAreas = sysAreaMapper.selectAll();
        // 将数据写出到excel表的对应sheet位置
        excel.write(sysAreas, sheet);
        // 关闭资源
        excel.finish();
        return outputStream;
    }

    /**
     * 将传入的excel文件的组成的inputStream流读取，转换成java对象，并且进行批量插入到数据库
     * @param inputStream
     * @return
     */
    @Override
    public int readExcel(InputStream inputStream) {
        int result = 0;
        ExcelReader excel = EasyExcel.read(inputStream, SysArea.class, new SysAreaListener(sysAreaMapper)).build();
        ReadSheet sheet = EasyExcel.readSheet(0).build();
        excel.read(sheet);
        excel.finish();
        return ++result;
    }

    @Override
    public PageInfo<SysArea> selectByCondition(Map<String, Object> map) {
        PageInfo<SysArea> pageInfo = null;
        if (StringUtils.isEmpty(map.get("pageNum"))) {
            map.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(map.get("pageSize"))) {
            map.put("pageSize", 4);
        }
        PageHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        if (map.containsKey("aid") && !StringUtils.isEmpty(map.get("aid"))) {
            int i = (int) map.get("aid");
            List<SysArea> aid = sysAreaMapper.selectByCondition((long) i);
            pageInfo = new PageInfo<>(aid);
        }
        return pageInfo;
    }

    @Override
    public SysArea selectAid(long id) {
        SysArea sysArea = sysAreaMapper.selectAid(id);
        sysArea.setOldParentIds(sysArea.getParentIds());    // 给旧parentIds绑定数据
        return sysArea;
    }

    @Override
    public int updateArea(SysArea sysArea) {
        int i = sysAreaMapper.updateByPrimaryKeySelective(sysArea);
        if (!sysArea.getOldParentIds().equals(sysArea.getParentIds())) {
            i += sysAreaMapper.updateParentIds(sysArea); // 更新所有的子区域的parentIds
        }
        return i;
    }


}

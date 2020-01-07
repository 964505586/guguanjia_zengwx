package com.dfbz.controller;

import com.baidu.ueditor.ActionEnter;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * ueditor的后端统一请求接口：
 * 1.引入依赖文件  包括需要自己打包到本地仓库的ueditor.jar
 * 2.编写统一请求接口：
 * a.提供读取config.json的action处理逻辑
 * b.体统处理图片等其他上传请求的处理逻辑
 */

@Controller
public class UeditorController {

    @Value("${upload}")
    private String upload;
    @Value("${path}")
    private String path;

    @RequestMapping("doExec")
    @ResponseBody
    public String doExec(String action, HttpServletRequest request, MultipartFile upfile) {
        String result = null;
        if ("config".equals(action)) {
            // 读取配置文件返回json串
            return new ActionEnter(request, request.getContextPath()).exec();
        }if ("uploadimage".equals(action)) {
            // 图片上传处理请求
            result = uploadimage(upfile);
        }
        return result;
    }

    private String uploadimage(MultipartFile upfile) {
        JSONObject jsonObject = null;
        // 获得源文件名
        String originalFilename = upfile.getOriginalFilename();
        String type = originalFilename.substring(originalFilename.lastIndexOf("."));

        if (!upfile.isEmpty()) {
            String fileName = UUID.randomUUID() + type;
            String targetFileName = upload + fileName;
            try {
                upfile.transferTo(new File(targetFileName));
                // originalFilename: 源文件名   upfile.getSize(): 文件大小  targetFileName: 文件存储路径  type: 文件类型  File.separator: 单双斜线
                jsonObject = new JSONObject(resultMap("SUCCESS", originalFilename, upfile.getSize(), targetFileName, type, path + File.separator + fileName));
            } catch (IOException e) {
                e.printStackTrace();
                jsonObject = new JSONObject(resultMap("FAIL", null, 0, null, null, null));
            }
        }
        return jsonObject.toString();
    }

    private Map<String, Object> resultMap(String state, String origina, long size, String title, String type, String url) {
        Map<String, Object> map = new HashMap<>();
        map.put("state", state);
        map.put("origina", origina);
        map.put("size", size);
        map.put("title", title);
        map.put("type", type);
        map.put("url", url);
        return map;
    }
}

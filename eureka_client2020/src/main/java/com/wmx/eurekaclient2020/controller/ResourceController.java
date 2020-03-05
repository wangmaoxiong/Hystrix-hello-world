package com.wmx.eurekaclient2020.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2018/3/8 0008.
 * 资源控制器
 *
 * @author wangmaoxiong
 */
@RestController
public class ResourceController {

    /**
     * 通过配置的方式确定上传文件存储的目录
     */
    @Value("${fileSaveDir}")
    private String fileSaveDir;

    /**
     * 单文件上传。其中不做文件大小、类型等的判断，IO异常的处理，只做简单的上传演示.
     *
     * @param uploadFile：上传的文件封装好的对象
     */
    @RequestMapping(value = "zebra/resource/upload", method = RequestMethod.POST)
    public String singleFileUpload(@RequestParam MultipartFile uploadFile) {
        File file = new File(fileSaveDir);
        String fileSizeString = "";
        try {
            if (uploadFile != null && uploadFile.getSize() > 0) {
                file = new File(file, uploadFile.getOriginalFilename());
                uploadFile.transferTo(file);
                fileSizeString = (uploadFile.getSize() / 1024) + "KB";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传文件保存路径为：" + file.getAbsolutePath() + "，文件大小：" + fileSizeString;
    }
}
package com.atguigu.oss.controller;

import com.atguigu.R;
import com.atguigu.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shkstart
 * @create 2021-05-19-11:21
 */

@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin

public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping
    public R uploadOssFile(MultipartFile file){

        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url",url);
    }
}

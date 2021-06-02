package com.atguigu.oss.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author shkstart
 * @create 2021-05-19-11:21
 */


public interface OssService {

    public  String uploadFileAvatar(MultipartFile file);


}

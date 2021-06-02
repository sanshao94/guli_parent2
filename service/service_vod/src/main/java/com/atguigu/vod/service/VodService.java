package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-05-25-16:56
 */
public interface VodService {
    String uploadAlyiVideo(MultipartFile file);

    void removeMoreAlyVideo(List<String> videoIdList);
}

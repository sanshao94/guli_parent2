package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantVodUtils;
import com.atguigu.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-05-25-16:56
 */

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file){
        String videoId = vodService.uploadAlyiVideo(file);
        System.out.println("***************************************");
        System.out.println(videoId);
        System.out.println("***************************************");
        return R.ok().data("videoId",videoId);
    }


    @DeleteMapping("removeAlyVideo/{id}")
    public  R removeAlyVideo(@PathVariable String id){

        try{
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.ok();
        }
        catch (Exception e){

            e.printStackTrace();
            throw new GuliException(20001,"删除失败");
        }

    }


    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList" ) List<String> videoIdList){

        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }
}

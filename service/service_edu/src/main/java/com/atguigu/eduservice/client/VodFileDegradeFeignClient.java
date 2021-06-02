package com.atguigu.eduservice.client;

import com.atguigu.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-05-27-11:06
 */

@Component
public class VodFileDegradeFeignClient implements VodClient{

    @Override
    public R removeAlyVideo(String id) {
        return R.error().message("time out");
    }

    @Override
    public R deleteBatch(List<String> videoIdList) {
        return R.error().message("time out");
    }
}

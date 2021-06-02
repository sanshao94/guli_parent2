package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-05-21-16:30
 */

@Data
public class ChapterVo {

    private String id;
    private String title;

    private List<VideoVo> children = new ArrayList<>();
}

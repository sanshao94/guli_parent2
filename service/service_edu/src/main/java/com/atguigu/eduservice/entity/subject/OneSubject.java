package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-05-20-14:32
 */

@Data
public class OneSubject {

    private String id;
    private String title;

    private List<TwoSubject> children = new ArrayList<>();
}

package com.atguigu.eduservice.controller.front;

import com.atguigu.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-06-01-17:04
 */

@RestController
@CrossOrigin
@RequestMapping("/eduservice/teacherfront")
public class TeacherFrontController {


    @Autowired
    private EduCourseService courseService;


    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping(value = "getTeacherFrontList/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
         @PathVariable Long limit){
        Page<EduTeacher> pageParam = new Page<EduTeacher>(page, limit);
        Map<String, Object> map = teacherService.pageListWeb(pageParam);
        return R.ok().data(map);
    }


    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "getTeacherFrontInfo/{teacherId}")
    public R getById(
            @ApiParam(name = "teacherId", value = "讲师ID", required = true)
            @PathVariable String teacherId){
        //查询讲师信息
        EduTeacher teacher = teacherService.getById(teacherId);


        //根据讲师id查询这个讲师的课程列表
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id",teacherId);

        List<EduCourse> courseList = courseService.list(wrapper);
        return R.ok().data("teacher", teacher).data("courseList", courseList);
    }
}

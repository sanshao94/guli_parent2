package com.atguigu.eduservice.controller;


import com.atguigu.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2021-04-27
 */
@RestController
@RequestMapping("/eduservice/teacher")
@Api(description="讲师管理")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;


    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items",list);
    }


    @ApiOperation(value = "根据ID删除讲师")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true)@PathVariable String id){
        boolean flag = teacherService.removeById(id);

        if(false){
            return R.ok();
        }else {
            return R.error();
        }
    }


    @GetMapping("pageTeacher/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current, @PathVariable long limit){

        Page<EduTeacher> pageTeacher = new Page<>(current, limit);

        teacherService.page(pageTeacher,null);

        long total = pageTeacher.getTotal();
        List<EduTeacher> records = pageTeacher.getRecords();
        return R.ok().data("total",total).data("rows",records);
    }










}


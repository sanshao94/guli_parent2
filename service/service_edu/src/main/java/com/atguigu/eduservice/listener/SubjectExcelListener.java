package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author shkstart
 * @create 2021-05-19-17:34
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {


    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

        if(subjectData == null){
            throw new GuliException(20001,"文件数据为空");

        }


        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());

        if(existOneSubject == null){
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }

        String pid = existOneSubject.getId();
        EduSubject existtwoSubject = this.existtwoSubject(subjectService, subjectData.getOneSubjectName(),pid);

        if(existtwoSubject == null){
            existtwoSubject = new EduSubject();
            existtwoSubject.setParentId(pid);
            existtwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existtwoSubject);
        }


    }

    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("title", name);
        wrapper.eq("parent_id","0");
        EduSubject oneSubject = subjectService.getOne(wrapper);

        return oneSubject;

    }

    private EduSubject existtwoSubject(EduSubjectService subjectService, String name,String pid) {

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();

        wrapper.eq("title", name);
        wrapper.eq("parent_id",pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);

        return twoSubject;

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}

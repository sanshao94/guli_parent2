package com.atguigu.educenter.service;

import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author sanshao
 * @since 2021-05-31
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getByid(String memberId);
}

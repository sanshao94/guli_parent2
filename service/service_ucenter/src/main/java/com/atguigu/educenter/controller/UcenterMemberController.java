package com.atguigu.educenter.controller;

import com.atguigu.JwtUtils;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.service.UcenterMemberService;

import com.atguigu.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author sanshao
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/educenter/member")

@CrossOrigin
public class UcenterMemberController {


    @Autowired
    private UcenterMemberService memberService;


    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody UcenterMember member) {
        String token = memberService.login(member);
        return R.ok().data("token1", token);

    }


    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        memberService.register(registerVo);
        return R.ok();
    }


    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request){
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);

            UcenterMember member = memberService.getByid(memberId);

            return R.ok().data("userInfo", member);
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001,"error");
        }
    }
}


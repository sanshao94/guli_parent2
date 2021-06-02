package com.atguigu.educenter.service.impl;

import com.atguigu.JwtUtils;
import com.atguigu.MD5;
import com.atguigu.educenter.entity.UcenterMember;
import com.atguigu.educenter.entity.vo.RegisterVo;
import com.atguigu.educenter.mapper.UcenterMemberMapper;
import com.atguigu.educenter.service.UcenterMemberService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author sanshao
 * @since 2021-05-31
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public UcenterMember getByid(String memberId) {

        UcenterMember member = baseMapper.selectById(memberId);


        return member;

    }

    @Override
    public String login(UcenterMember member) {
        String mobile = member.getMobile();
        String password = member.getPassword();

//校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password) ) {
            throw new GuliException(20005,"error");
        }

//获取会员

        UcenterMember mobilemember = baseMapper.selectOne(new QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(null == mobilemember) {
            throw new GuliException(20002,"error");
        }
//校验密码
        if(!MD5.encrypt(password).equals(mobilemember.getPassword())) {
            throw new GuliException(20003,"error");
        }
//校验是否被禁用
        if(mobilemember.getIsDisabled()) {
            throw new GuliException(20004,"error");
        }
//使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(mobilemember.getId(), mobilemember.getNickname());

        return token;
    }

    @Override
    public void register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();



//校验参数
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw new GuliException(20001,"error");
        }
//校验校验验证码
//从redis获取发送的验证码


//查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new
                QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if(count.intValue() > 0) {
            throw new GuliException(20001,"error");
        }
//添加注册信息到数据库


        UcenterMember member = new UcenterMember();
        member.setNickname(nickname);
        member.setMobile(registerVo.getMobile());
        member.setPassword(MD5.encrypt(password));
        member.setIsDisabled(false);
        member.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(member);
    }


}

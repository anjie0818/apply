package com.anjie.apply.service;

import com.anjie.apply.domain.SysUser;
import com.anjie.apply.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomerUserService implements UserDetailsService{//自定义需要实现UserDetailService的接口
    @Autowired
    SysUserRepository sysUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//重写此方法获取用户
        SysUser user = sysUserRepository.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;//当前用户实现的UserDetail所以可以直接返回给spring security
    }
}

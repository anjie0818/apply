package com.anjie.apply.repository;

import com.anjie.apply.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserRepository extends JpaRepository<SysUser,Long> {
//    按照用户名查用户
    SysUser findByUsername(String username);
}

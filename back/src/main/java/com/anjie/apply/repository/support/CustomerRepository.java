package com.anjie.apply.repository.support;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
@NoRepositoryBean //标注此接口不是领域类的接口
/**
 * 此接口具备JpaRespository的方法，继承类JpaSpecificationExecutor具备Specification的能力
 */
public interface CustomerRepository <T,ID extends Serializable> extends JpaRepository<T,ID>,JpaSpecificationExecutor<T>{
    Page<T> findByAuto(T example, Pageable pageable);
}

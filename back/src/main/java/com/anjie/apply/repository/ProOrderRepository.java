package com.anjie.apply.repository;


import com.anjie.apply.domain.ProOrder;
import com.anjie.apply.repository.support.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by sang on 2016/12/30.
 */
public interface ProOrderRepository extends JpaRepository<ProOrder, Long> ,JpaSpecificationExecutor<ProOrder>{
    @RestResource(path="withVersionQuery",rel="withVersionQuery")
//    使用的是实体类注解的namedQuery()进行查询，而不是根据接口中的方法查询
   List<ProOrder> withVersionQuery(String versionQuery);
//   使用sql进行查询
    @Query("select p from ProOrder p  where p.version=?1")
    List<ProOrder> findByVersion(String version);
//    排序
    List<ProOrder> findByOrderStatus(String orderStatus, Sort sort);
//    分页
    Page<ProOrder> findByOrderStatus(String orderStatus, Pageable pageable);
    List<ProOrder> findByOrOrderStatusAndBuyDate(String OrderStatus,String bugDate);
}

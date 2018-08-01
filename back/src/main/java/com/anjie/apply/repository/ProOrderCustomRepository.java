package com.anjie.apply.repository;


import com.anjie.apply.domain.ProOrder;
import com.anjie.apply.repository.support.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by sang on 2016/12/30.
 */
public interface ProOrderCustomRepository extends CustomerRepository<ProOrder,Long>{
    List<ProOrder> findByVersion(String version);
    ProOrder findByVersionAndOrderStatus(String version,String orderStatus);
    @Query("select p from ProOrder p where p.version=:version and p.orderStatus=:orderStatus")
    ProOrder withVersionAndOrderStatus(@Param("version") String version,@Param("orderStatus")String orderStatus);
}

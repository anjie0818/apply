package com.anjie.apply.repository;

import com.anjie.apply.domain.ProOrder;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class CustomerSpecs {
    public static Specification<ProOrder> proOrderFromVersion(){
        return new Specification<ProOrder>() {
            @Override
            public Predicate toPredicate(Root<ProOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get("version"),"0");
            }
        };
    }
}

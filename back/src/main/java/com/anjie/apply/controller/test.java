package com.anjie.apply.controller;

import com.anjie.apply.domain.ProductOrder;
import com.anjie.apply.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;

@RestController
public class test {
    @Autowired
    ProductOrderRepository productOrderRepository;
    @GetMapping(value = "/test")
    public String girls() {
        long ss = productOrderRepository.count();
        System.out.println(ss);
        return "anjie";
    }
}

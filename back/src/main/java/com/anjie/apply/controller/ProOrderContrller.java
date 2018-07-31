package com.anjie.apply.controller;

import com.anjie.apply.domain.ProOrder;
import com.anjie.apply.repository.ProOrderRepository;
import com.anjie.apply.tool.Tool;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
public class ProOrderContrller {
    @Autowired
    ProOrderRepository proOrderRepository;
    @RequestMapping(value = "api/createOrder",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
    public String createOrder(@RequestBody ProOrder  proOrder) throws IOException {
        //生成orderno
        proOrder.setOrderNo(Tool.createOrderno());
        proOrder.setOrderStatus("0");
        proOrder.setProduct("2");
        proOrder.setDate(Tool.getDate());
        proOrderRepository.save(proOrder);
        return proOrder.getOrderNo();
    }
    @RequestMapping(value = "api/getOrderList",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
    public HashMap<String ,List<ProOrder>> getOrderList(@RequestBody String  reqParam) throws IOException {

        List<ProOrder> orders = proOrderRepository.findAll();
        HashMap<String ,List<ProOrder>> ordersmap=new HashMap<>();
        ordersmap.put("list",orders);
        return ordersmap;
    }

}

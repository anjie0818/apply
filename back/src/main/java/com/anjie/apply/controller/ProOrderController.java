package com.anjie.apply.controller;

import com.anjie.apply.controller.warpper.ProOrderW;
import com.anjie.apply.domain.ProOrder;
import com.anjie.apply.repository.ProOrderCustomRepository;
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
import java.util.Map;

@RestController
public class ProOrderController {
    @Autowired
    ProOrderCustomRepository proOrderCustomRepository;
    @RequestMapping(value = "api/createOrder",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
    public String createOrder(@RequestBody ProOrder  proOrder) throws IOException {
        //生成orderno
        proOrder.setOrderNo(Tool.createOrderno());
        proOrder.setOrderStatus("0");
        proOrder.setDate(Tool.getDateDetail());
        proOrderCustomRepository.save(proOrder);
        return proOrder.getOrderNo();
    }
    @RequestMapping(value = "api/delete",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
    public String delete( @RequestBody ProOrder  proOrder) throws IOException {
        System.out.println(proOrder.getId());
        proOrderCustomRepository.deleteById(Long.valueOf(proOrder.getId()));
        return "true";
    }
    @RequestMapping(value = "api/getOrderList",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
    public HashMap<String ,List<ProOrder>> getOrderList(@RequestBody String  reqParam) throws IOException {

        List<ProOrder> orders = proOrderCustomRepository.findAll();
        List<ProOrder> orderWapper = (List<ProOrder>) new ProOrderW(orders).warp();
        HashMap<String ,List<ProOrder>> ordersmap=new HashMap<>();
        ordersmap.put("list",orderWapper);
        return ordersmap;
    }
    @RequestMapping(value = "api/getOrderByOrderNo",method=RequestMethod.POST,produces="application/json; charset=UTF-8")
    public HashMap<String ,ProOrder> getOrderByOrderId(@RequestBody ProOrder  proOrder) throws IOException {

        ProOrder proOrderRes = proOrderCustomRepository.findByOrderNo(proOrder.getOrderNo());
        HashMap<String ,ProOrder> ordersmap=new HashMap<>();
        ordersmap.put("proOrder",proOrderRes);
        return ordersmap;
    }

}

package com.anjie.apply.controller;

import com.anjie.apply.domain.ProOrder;
import com.anjie.apply.repository.CustomerSpecs;
import com.anjie.apply.repository.ProOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class test {
    @Autowired
    private ProOrderRepository proOrderRepository;
    @GetMapping(value = "/findAll")
    public ProOrder girls() {
        Object ss = proOrderRepository.findAll();
        System.out.println(ss);
        return null;
    }
    @RequestMapping(value = "/findVersion",method= RequestMethod.GET,produces="application/json; charset=UTF-8")
    public List<ProOrder> findByVersion(){
        List<ProOrder> proOrders = proOrderRepository.findAll(CustomerSpecs.proOrderFromVersion());
        return proOrders;
    }
    @RequestMapping(value = "/sort",method= RequestMethod.GET,produces="application/json; charset=UTF-8")
    public List<ProOrder> sort(){
        return proOrderRepository.findByOrderStatus("0",new Sort(Sort.Direction.ASC,"bankId"));
    }
    @RequestMapping(value = "/page",method= RequestMethod.GET,produces="application/json; charset=UTF-8")
    public List<ProOrder> page(){
        Page<ProOrder> page = proOrderRepository.findByOrderStatus("0", new PageRequest(1, 2));
        return null;
    }
    @RequestMapping(value = "/save",method= RequestMethod.GET,produces="application/json; charset=UTF-8")
    public ProOrder save(){
        ProOrder proOrder=proOrderRepository.save(new ProOrder("save","save","save","save","save","save","save","save","save","save"));
        return proOrder;
    }
    @RequestMapping("/delete")
    public  String delete(){
        proOrderRepository.deleteById((long) 10);
        return "delete";
    }



}

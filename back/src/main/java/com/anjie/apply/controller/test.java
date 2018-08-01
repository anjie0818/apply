package com.anjie.apply.controller;

import com.anjie.apply.domain.ProOrder;
import com.anjie.apply.repository.ProOrderCustomRepository;
import com.anjie.apply.repository.support.CustomerSpecs;
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

@RestController
public class test {
    @Autowired
    private ProOrderRepository proOrderRepository;
    @Autowired
    private ProOrderCustomRepository proOrderCustomRepository;
    @RequestMapping(value = "/cuspage",method= RequestMethod.GET,produces="application/json; charset=UTF-8")
    public Page<ProOrder> cuspage(){
        Page<ProOrder> page = proOrderCustomRepository.findAll( new PageRequest(0,10));
        return page;
    }
    @RequestMapping(value = "/auto",method= RequestMethod.GET,produces="application/json; charset=UTF-8")
    public Page<ProOrder> auto(){
        Page<ProOrder> page = proOrderCustomRepository.findByAuto(new ProOrder("","","","","","","save1","","",""), new PageRequest(0,10));
        return page;
    }
    @GetMapping(value = "/findAll")
    public ProOrder girls() {
        Object ss = proOrderRepository.findAll();
        System.out.println(ss);
        return null;
    }

    @RequestMapping(value = "/sort",method= RequestMethod.GET,produces="application/json; charset=UTF-8")
    public List<ProOrder> sort(){
        return proOrderRepository.findByOrderStatus("0",new Sort(Sort.Direction.ASC,"bankId"));
    }
    @RequestMapping(value = "/page",method= RequestMethod.GET,produces="application/json; charset=UTF-8")
    public Page<ProOrder> page(){
        Page<ProOrder> page = proOrderRepository.findAll( new PageRequest(1,2));
        return page;
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

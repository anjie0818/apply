package com.anjie.apply.controller.warpper;

import com.anjie.apply.domain.ProOrder;
import com.anjie.apply.domain.ProOrderDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @创建人 anjie
 * @创建时间 2018/8/2
 * @描述
 */
public class ProOrderW extends BaseControllerWarpper
{

    public ProOrderW(List<ProOrder> list) {
        super(list);
    }

    @Override
    public ProOrderDetail warpTheMap(ProOrder proOrder) {
        HashMap<String,String> versionMap=new HashMap<>();
        versionMap.put("0","客户版 ");
        versionMap.put("1","代理商版 ");
        versionMap.put("2","专家版 ");
        HashMap<String,String> periodMap=new HashMap<>();
        periodMap.put("0","半年");
        periodMap.put("1","一年");
        periodMap.put("2","三年");
        HashMap<String,String> buyTypeMap=new HashMap<>();
        buyTypeMap.put("0","入门版");
        buyTypeMap.put("1","中级版");
        buyTypeMap.put("2","高级版");

        String verionName="";
        //处理version
        String versionStr =proOrder.getVersion();
        String[] versionArr = versionStr.split(",");
        for (String version:versionArr
             ) {
            verionName+=versionMap.get(version);
        }
        //处理period
        String period = proOrder.getPeriod();
        String periodName=periodMap.get(period);
        //处理buyType
        String buyType=proOrder.getBuyType();
        String buyTypeName=buyTypeMap.get(buyType);
        //包装
        ProOrderDetail proOrderDetail=new ProOrderDetail(proOrder.getId(),proOrder.getOrderNo(),proOrder.getProduct(),proOrder.getBuyType(),proOrder.getPrice(),proOrder.getBuyNumber(),
                proOrder.getPeriod(),proOrder.getVersion(),verionName,proOrder.getBankId(),proOrder.getDate(),proOrder.getOrderStatus(),periodName,buyTypeName);
       return proOrderDetail;
    }
}

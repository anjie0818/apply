package com.anjie.apply.controller.warpper;

import com.anjie.apply.domain.ProOrder;
import com.anjie.apply.domain.ProOrderWarpper;

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
    public ProOrderWarpper warpTheMap(ProOrder proOrder) {
        HashMap<String,String> versionMap=new HashMap<>();
        versionMap.put("0","客户版 ");
        versionMap.put("1","代理商版 ");
        versionMap.put("2","专家版 ");
        String verionName="";
        //处理version
        String versionStr =proOrder.getVersion();
        String[] versionArr = versionStr.split(",");
        for (String version:versionArr
             ) {
            verionName+=versionMap.get(version);
        }
        ProOrderWarpper proOrderWarpper=new ProOrderWarpper();
        proOrderWarpper.setVersionName(verionName);
        proOrderWarpper.setOrderNo(proOrder.getOrderNo());
       return proOrderWarpper;
    }
}

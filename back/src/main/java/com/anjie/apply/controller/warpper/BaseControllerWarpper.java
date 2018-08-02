package com.anjie.apply.controller.warpper;


import com.anjie.apply.domain.ProOrder;
import com.anjie.apply.domain.ProOrderWarpper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 控制器查询结果的包装类基类
 *
 * @author fengshuonan
 * @date 2017年2月13日 下午10:49:36
 */
public abstract class BaseControllerWarpper {

    public Object obj = null;

    public BaseControllerWarpper(Object obj) {
        this.obj = obj;
    }

    @SuppressWarnings("unchecked")
    public Object warp() {
        if (this.obj instanceof List) {
            List<ProOrder> list = (List<ProOrder>) this.obj;
            List<ProOrderWarpper> listw = new ArrayList<>();

            for (int i=0;i<list.size();i++){
                ProOrderWarpper proOrderWarpper=warpTheMap(list.get(i));
                listw.add(proOrderWarpper);
            }

            return listw;
        } else if (this.obj instanceof ProOrder) {
            return  warpTheMap((ProOrder) obj);

        } else {
            return this.obj;
        }
    }

    public abstract ProOrderWarpper warpTheMap(ProOrder proOrder);
}

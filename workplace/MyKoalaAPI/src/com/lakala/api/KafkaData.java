package com.lakala.api;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONObject;

import com.lakala.config.Config;
import com.lakala.dict.Dict;
import com.lakala.util.HttpUtil;
import com.lakala.util.SignUtil;

public class KafkaData {
    // 业务系统名
    private String group = "1";

    // 不同业务系统的具体接口编码，相同系统内的编码长度尽量不要相同，防止都分配到同一partition，仅能给一个consumer处理
    private String businessCode;

    // 不同业务系统的具体接口处理类
    private String businessValue;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode;
    }

    public String getBusinessValue() {
        return businessValue;
    }

    public void setBusinessValue(String businessValue) {
        this.businessValue = businessValue;
    }

    public void qryGroup(String c){
    	if(!StringUtils.isEmpty(c)){
    		group = "2";
    	}
    	System.out.println("group:"+group);
    }
//    public static void main(String[] args) {
//        KafkaData model = new KafkaData();
//        model.setGroup("CMS");
//        model.setBusinessCode("cms.customer");
//        model.setBusinessValue("cmsCustomerExecutor");
//
//        KafkaData model2 = new KafkaData();
//        model2.setGroup("CMS");
//        model2.setBusinessCode("cms.customer");
//        model2.setBusinessValue("cmsContractExecutor");
//
//        System.out.println(Arrays.asList(new KafkaData[] { model, model2 }));
//
//    }
}


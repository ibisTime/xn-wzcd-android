package com.cdkj.wzcd.util;

import com.cdkj.baselibrary.model.DataDictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cdkj on 2018/6/7.
 */

public class AdvanceFundHelper {


    private Map<String,String> advanceFund = new HashMap<>();

    public AdvanceFundHelper(){
        build();
    }

    private void build(){

        advanceFund.put("0","否");
        advanceFund.put("1","是");

    }

    public AdvanceFundHelper setAdvanceMapFoundMap(Map<String,String> map){
        advanceFund = map;

        return this;
    }

    public Map<String,String> getAdvanceMapFoundMap(){
        return advanceFund;
    }

    public List<DataDictionary> getAdvanceMapFoundList(){
        // 返回符合数据字典规范的List
        List<DataDictionary> data = new ArrayList<>();

        for (Map.Entry<String, String> entry : advanceFund.entrySet()) {
            data.add(new DataDictionary().setDkey(entry.getKey()).setDvalue(entry.getValue()));
        }

        return data;
    }

    public String getValueWithKey(String key){
        return advanceFund.get(key);
    }


}

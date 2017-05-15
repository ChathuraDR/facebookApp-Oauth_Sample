package com.chathura.oauth.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChathuraDR on 5/14/2017.
 */
public class storeData {
    private static volatile Map<String, String> resourceMap;
    private static storeData resourceDataHolder;

    private storeData() {

        resourceMap = new HashMap<String, String>();
    }

    public static storeData getInstance() {

        if(resourceDataHolder == null){

            synchronized (storeData.class) {
                if(resourceDataHolder == null) {
                    resourceDataHolder = new storeData();
                }
            }
        }

        return resourceDataHolder;
    }

    public void addResource(String key, String value){
        resourceMap.put(key, value);
    }

    public String getResource(String key){
        return resourceMap.get(key);
    }
}

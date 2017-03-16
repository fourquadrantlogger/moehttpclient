package com.github.timeloveboy.cachecenter;

import okhttp3.Cookie;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by timeloveboy on 17-3-15.
 */
public class domain_cookie {
    static  Map<String, Map<String, Cookie>> cookieDB;
    static {
        cookieDB=new ConcurrentHashMap<>();
    }
    public static Map<String, Cookie> GetSiteCookies(String site){
        return cookieDB.get(site);
    }

    public static void AddCookie(String site,String name ,Cookie c){
        if(!cookieDB.containsKey(name)){
            cookieDB.put(site,new HashMap<String, Cookie>());
        }
        cookieDB.get(site).put(name,c);
    }
    public static void AddCookie(String site,String name ,String  value){
        if(!cookieDB.containsKey(name)){
            cookieDB.put(site,new HashMap<String, Cookie>());
        }
        Cookie.Builder cb=new Cookie.Builder();
        cb.name(name).value(value);
        cookieDB.get(site).put(name,cb.build());
    }
}

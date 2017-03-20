package com.github.timeloveboy.utils;

import okhttp3.Cookie;
import okhttp3.Headers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by timeloveboy on 17-3-16.
 */
public class CookieUtil {
    public static Map<String,String> parse(Headers origin){
        Map m=origin.toMultimap();
        List<String> ls= (List<String>) m.get("set-cookie");

        Map<String,String> cookies=new HashMap<>();
        if(ls==null){
            return cookies;
        }
        for (String s:ls) {
            String[] propertys=s.split("; ");
            for (String property:propertys) {
                String[] k_v=property.split("=");
                if (k_v.length == 2
                        && !k_v[0].toLowerCase().equals("domain")
                        && !(k_v[0]).toLowerCase().equals("path")
                        && !k_v[0].toLowerCase().equals("max-age")
                        && !k_v[0].toLowerCase().equals("expires")
                        ) {
                    cookies.put(k_v[0],k_v[1]);

                    if (k_v[1].toLowerCase().equals("deleteme")) {
                        cookies.remove(k_v[0]);
                    }
                }
            }
        }
        return cookies;
    }

    public static String cookieraw_fromstring(Map<String,String> cookies){
        String cookieraw="";
        if(cookies!=null&&cookies.size()!=0) {
            for (String key : cookies.keySet()) {
                String value = cookies.get(key);
                String cookie=key+"="+value;
                cookieraw+=cookie+"; ";
            }
            cookieraw=cookieraw.substring(0,cookieraw.length()-2);
        }
        return cookieraw;
    }
    public static String cookieraw_fromcookie(Set<Cookie> cookies){
        String cookieraw="";
        if(cookies!=null&&cookies.size()!=0) {
            for (Cookie value: cookies) {

                String cookie=value.name()+"="+value.value();
                cookieraw+=cookie+"; ";
            }
            cookieraw=cookieraw.substring(0,cookieraw.length()-2);
        }
        return cookieraw;
    }
}

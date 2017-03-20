package com.github.timeloveboy.utils;

import okhttp3.Cookie;
import okhttp3.Response;

import java.util.*;

/**
 * Created by timeloveboy on 17-3-16.
 */
public class CookieUtil {
    public static Set<Cookie> parse(Response resp) {
        Map m = resp.headers().toMultimap();
        List<String> ls= (List<String>) m.get("set-cookie");

        Map<String, Cookie> cookies = new HashMap<>();
        if(ls==null){
            return new HashSet<>();
        }
        for (String s:ls) {
            Cookie.Builder cb = new Cookie.Builder();
            String[] propertys=s.split("; ");
            String key = null, value = null, domain = null;
            for (String property:propertys) {
                String[] k_v=property.split("=");
                if (k_v.length == 1) {
                    switch (k_v[0]) {
                        case "HttpOnly": {
                            cb.httpOnly();
                            break;
                        }
                    }
                }
                if (k_v.length == 2) {
                    switch (k_v[0].toLowerCase()) {
                        case "domain": {
                            domain = k_v[1];
                            cb.domain(k_v[1]);
                            break;
                        }
                        case "path": {
                            cb.path(k_v[1]);
                            break;
                        }
                        case "max-age": {
                            //
                            break;
                        }
                        case "expires": {
                            break;
                        }
                        default: {
                            key = k_v[0];
                            value = k_v[1];
                        }
                    }

                }
            }
            if (key == null) {
                continue;
            }
            if (domain == null) {
                cb.domain(resp.request().url().host());
            }
            cb.name(key);
            cb.value(value);
            Cookie c = cb.build();
            cookies.put(key, c);

            if (value.toLowerCase().equals("deleteme")) {
                cookies.remove(key);
            }
        }
        Set<Cookie> result = new HashSet<>();
        for (String k : cookies.keySet()) {
            result.add(cookies.get(k));
        }
        return result;
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

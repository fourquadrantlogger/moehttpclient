package com.github.timeloveboy.moehttpclient.cachecenter;

import okhttp3.Cookie;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;


/**
 * Created by timeloveboy on 17-3-15.
 */
public class domain_cookie {
    Set<Cookie> cookieDB = new CopyOnWriteArraySet<>();

    public Set<Cookie> GetSiteCookies(String site){
        Set<Cookie> result=new HashSet<>();
        for(Cookie c:cookieDB){
            if (c.domain().contains(site)) {
                result.add(c);
            }
        }

        return result;
    }

    public Cookie GetCookie(String site, String name) {

        for (Cookie c : cookieDB) {
            if (c.domain().contains(site) && c.name().equals(name)) {
                return c;
            }
        }

        return null;
    }
    public void AddCookie(Cookie c){
        cookieDB.add(c);
    }

    public void AddCookie(String site,String name ,String value){
        Cookie.Builder cb=new Cookie.Builder();
        cb.name(name).value(value).domain(site);
        Cookie c=cb.build();
       if(!cookieDB.contains(c)){
           cookieDB.add(c);
       }
    }
}

package com.github.timeloveboy.moehttpclient.cachecenter;

import com.github.timeloveboy.utils.Log;
import okhttp3.Cookie;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Created by timeloveboy on 17-3-15.
 */
public class domain_cookie {
    Map<String, Cookie> cookieDB = new ConcurrentHashMap<>();

    public Set<Cookie> GetSiteCookies(String site) {
        Set<Cookie> result = new HashSet<>();
        for (String s : cookieDB.keySet()) {

            Cookie c = cookieDB.get(s);
            if (site.length() >= c.domain().length()) {
                //全局域名
                String global_domain = site.substring(site.length() - c.domain().length());
                if (global_domain.equals(c.domain())) {
                    result.add(c);
                }
            }

        }

        return result;
    }

    public Set<Cookie> GetAllCookies() {
        Set<Cookie> result = new HashSet<>();
        for (String s : cookieDB.keySet()) {
            Cookie c = cookieDB.get(s);
            result.add(c);
        }
        return result;
    }

    public Cookie GetCookie(String site, String name) {
        for (Cookie c : GetSiteCookies(site)) {
            if (c.name().equals(name)) {
                return c;
            }
        }

        return null;
    }

    public void AddCookie(Cookie c) {
        if (cookieDB.containsKey(c.name())) {
            Log.v(cookieDB.get(c.name()), "更新为", c);
        } else {
            Log.v(c.name(), "新增", c);
        }
        cookieDB.put(c.name(), c);
    }
}

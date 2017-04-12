package com.github.timeloveboy.moehttpclient.storage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by timeloveboy on 17-3-22.
 */
public class BrowserState {
    MemoryCookieStore cookies;
    List<Reqlog> requestshistory;

    {
        requestshistory = new ArrayList<>();
        cookies = new MemoryCookieStore();
    }

    public MemoryCookieStore getCookies() {
        return cookies;
    }

    public List<Reqlog> getRequestshistory() {
        return requestshistory;
    }

    public void addRequest(Reqlog req) {
        requestshistory.add(req);
    }

    public URL nowurl() {
        URL now = null;
        try {
            now = new URL(requestshistory.get(requestshistory.size() - 1).url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return now;
    }

    public boolean SessionOK() {
        Long span = new Date().getTime() - requestshistory.get(requestshistory.size() - 1).t.getTime();
        if (span > 15 * 60 * 1000) {
            return false;
        } else return true;
    }
}

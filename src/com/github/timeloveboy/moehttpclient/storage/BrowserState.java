package com.github.timeloveboy.moehttpclient.storage;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by timeloveboy on 17-3-22.
 */
public class BrowserState {
    MemoryCookieStore cookies;
    List<String> requestshistory;

    {
        requestshistory = new ArrayList<>();
        cookies = new MemoryCookieStore();
    }

    public MemoryCookieStore getCookies() {
        return cookies;
    }

    public List<String> getRequestshistory() {
        return requestshistory;
    }

    public void addRequest(String req) {
        requestshistory.add(req);
    }

    public URL nowurl() {
        URL now = null;
        try {
            now = new URL(requestshistory.get(requestshistory.size() - 1).substring(4));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return now;
    }
}

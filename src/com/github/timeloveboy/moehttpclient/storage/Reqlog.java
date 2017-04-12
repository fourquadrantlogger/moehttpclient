package com.github.timeloveboy.moehttpclient.storage;

import java.util.Date;

/**
 * Created by timeloveboy on 17-4-12.
 */
public class Reqlog {
    public String url;
    public String method;
    public Date t;

    public Reqlog(String method, String url) {
        this.url = url;
        this.method = method;
        this.t = new Date();
    }
}

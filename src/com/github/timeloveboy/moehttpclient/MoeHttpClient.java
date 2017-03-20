package com.github.timeloveboy.moehttpclient;

import com.github.timeloveboy.moehttpclient.cachecenter.MemoryCookieStore;
import com.github.timeloveboy.utils.Log;
import okhttp3.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by timeloveboy on 17-3-15.
 */
public class MoeHttpClient {
    Request.Builder requestbuilder = new Request.Builder();
    OkHttpClient client;
    URL u;
    MemoryCookieStore cookieStore = new MemoryCookieStore();
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cookieJar(cookieStore);
        builder.followRedirects(false);
        builder.followSslRedirects(false);
        client = builder.build();
    }

    public MoeHttpClient() {

    }


    public MoeHttpClient(MemoryCookieStore cookiecenter) {
        this.cookieStore = cookiecenter;
    }

    public URL getlastReqUrl() {
        return u;
    }

    public Request.Builder getRequestbuilder() {
        return requestbuilder;
    }

    public MemoryCookieStore getCookiecenter() {
        return cookieStore;
    }

    public MoeHttpClient GET(String url)throws MalformedURLException{
        requestbuilder.get().url(url);
        u = new URL(url);
        return this;
    }
    public MoeHttpClient header(String key,String value){
        requestbuilder.header(key, value);
        return this;
    }

    public Response execute() {
        Request request = requestbuilder
                .build();
        Response response;
        try {
            response= client.newCall(request).execute();
        }catch (Exception e){
            e.printStackTrace();
            response=null;
        }
        return response;
    }

    public Response execute_location() throws Exception {
        Response response = execute();
        if(response.header("Location")!=null){
            String ur = response.header("Location");
            Log.v(u, " 重定向 ", ur);
            requestbuilder.url(ur).get();
            u = new URL(ur);
            response = execute_location();
        }
        return response;
    }
    //HttpMethod method,
    public MoeHttpClient POST(String url, Map<String,String> form)throws MalformedURLException{

        FormBody.Builder reqbuilder = new FormBody.Builder();
        for (String key : form.keySet()) {
            String value = form.get(key);
            reqbuilder.add(key,value);
        }
        RequestBody formBody = reqbuilder.build();
        requestbuilder.url(url).post(formBody);
        u = new URL(url);
        return this;
    }
}

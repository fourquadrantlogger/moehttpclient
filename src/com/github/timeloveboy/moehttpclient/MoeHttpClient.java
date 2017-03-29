package com.github.timeloveboy.moehttpclient;

import com.github.timeloveboy.moehttpclient.storage.BrowserState;
import com.github.timeloveboy.utils.CookieUtil;
import com.github.timeloveboy.utils.Log;
import okhttp3.*;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.Set;

/**
 * Created by timeloveboy on 17-3-15.
 */
public class MoeHttpClient {
    Request.Builder requestbuilder = new Request.Builder();
    BrowserState browserState = new BrowserState();
    OkHttpClient client;


    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.followRedirects(false);
        builder.followSslRedirects(false);
        client = builder.build();
    }

    public MoeHttpClient() {

    }


    public MoeHttpClient(BrowserState b) {
        this.browserState = b;
    }


    public Request.Builder getRequestbuilder() {
        return requestbuilder;
    }

    public BrowserState getBrowserState() {
        return browserState;
    }

    public MoeHttpClient GET(String url)throws MalformedURLException{

        requestbuilder.get().url(url);
        browserState.addRequest("GET " + url);
        return this;
    }
    public MoeHttpClient header(String key,String value){
        requestbuilder.header(key, value);
        return this;
    }

    public Response execute() {
        Set<Cookie> cs = browserState.getCookies().GetSiteCookies(browserState.nowurl().getHost());
        String cookieraw = CookieUtil.cookieraw_fromcookie(cs);
        Log.v("使用cookie", "\t", cookieraw);
        requestbuilder.header("Cookie", cookieraw);
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

    public Response execute_andsavecookies() {
        Response response = execute();
        if (response == null) {
            return null;
        }
        Set<Cookie> addcookie = CookieUtil.parse(response);

        for (Cookie c : addcookie) {
            browserState.getCookies().AddCookie(c);
        }

        return response;
    }

    public Response execute_andsavecookies_location() throws Exception {
        Response response = execute_andsavecookies();
        if (response == null) {
            return null;
        }

        if(response.header("Location")!=null){
            response.close();

            String ur = response.header("Location");
            Log.v(browserState.nowurl(), " 重定向 ", ur);
            requestbuilder.url(ur).get();
            browserState.addRequest("GET " + ur);
            Response newresponse = execute_andsavecookies_location();
            return newresponse;
        }

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
        browserState.addRequest("POST " + url);
        return this;
    }
}

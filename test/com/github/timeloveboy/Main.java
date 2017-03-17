package com.github.timeloveboy;

import com.github.timeloveboy.moehttpclient.MoeHttpClient;
import okhttp3.Response;

import java.util.Date;


public class Main {

    public static void main(String[] args)throws Exception {
	// write your code here
        MoeHttpClient gab=new MoeHttpClient();
        Response res_index=gab.GET("https://gab.122.gov.cn/m/login").execute_andsavecookies();

        String url_captche="https://gab.122.gov.cn/sso/captcha?nocache="+(new Date().getTime());
        Response res_captcha = gab.GET(url_captche).execute_andsavecookies_location();



    }
}

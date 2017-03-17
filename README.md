# moehttpclient
```

    ______        _____
    |_    \      /    _|
      | |\ \    / /| |          _____       _____
      | | \ \__/ / | |         /  _  \     / ____\
     _| |_ \____/ _| |_       |  |_|  |   | ____  |
    |_____|      |_____|       \_____/     \_____|

```
>萌萌哒的java 爬虫专用 httpclient

由于OKHttpclient 在执行301重定向的中间过程，cookie没有被保存，这回导致爬虫请求丢失cookie。

moehttpclient基于okhttpclient，对此进行了修正。

+ 0.0.1 采用了okhttpclient


## using maven

```
<dependency>
  <groupId>com.github.timeloveboy</groupId>
  <artifactId>moehttpclient</artifactId>
  <version>0.0.1</version>
</dependency>
```

## 使用说明

参考test目录示例代码

 + .execute()
 加载MoeHttpClient的cookie，发送（禁止重定向的）请求，并返回response

 + .execute_andsavecookies()
 加载MoeHttpClient的cookie，发送（禁止重定向的）请求，自动保存set-cookie，并返回response

  + .execute_andsavecookies_location()
  加载MoeHttpClient的cookie，发送（禁止重定向的）请求，自动保存set-cookie，自动重定向，并返回response

```
        MoeHttpClient gab=new MoeHttpClient();
        Response res_index=gab.GET("https://.../m/login").execute();

        String url_captche="https://.../sso/captcha?nocache="+(new Date().getTime());
        Response res_captcha=gab.GET(url_captche).execute_andsavecookies();

        Response res_captcha=gab.GET(url_captche).execute_andsavecookies_location();
```
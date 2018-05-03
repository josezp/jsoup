package com.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;

/**
 * @Author:JoseZ
 * @Description:
 * @Date:Created in 2018/5/3 11:49
 * @Modified By:
 */
public class Demo {
    public static  final String returnCookies() {
        try {
            Connection conn = Jsoup.connect("https://xueqiu.com");
            conn.method(Connection.Method.GET);
            conn.followRedirects(false);
            Connection.Response response;
            response = conn.execute();
            Map<String, String> getCookies = response.cookies();
            String Cookie = getCookies.toString();
            Cookie = Cookie.substring(Cookie.indexOf("{")+1, Cookie.lastIndexOf("}"));
            Cookie = Cookie.replaceAll(",", ";");
            return Cookie;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static String goHttp() {

        try {
            Connection conn = Jsoup.connect("https://xueqiu.com/v4/stock/quote.json?code=SH600000");
            conn.header("Host","xueqiu.com");
            conn.header("Connection","keep-alive");
            conn.header("Cache-Control","max-age=0");
            conn.header("Upgrade-Insecure-Requests","1");
            conn.header("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            conn.header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.header("Accept-Language","zh-CN,zh;q=0.8");
            conn.header("Cookie",returnCookies());
            conn.method(Connection.Method.GET);
            conn.followRedirects(false);
            conn.ignoreContentType(true);
            Connection.Response response = conn.execute();
            String body = response.body();
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    public static void main(String[] args) {
        goHttp();
    }
}

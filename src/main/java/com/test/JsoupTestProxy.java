package com.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author:JoseZ
 * @Description:
 * @Date:Created in 2018/5/3 19:45
 * @Modified By:
 */
public class JsoupTestProxy {
    public static List<String> lists = new ArrayList<String>();
    public static Scanner input = new Scanner(System.in);
    public static int i = 0;
    String filePath = "";

    public static void main(String[] args) {
        try {
            System.out.println("输入网址 , 号隔开");
            String path = "https://unsplash.com/@gaspanik";
            String[] paths = path.split(",");
            for (String pa : paths) {
                System.out.println("开始下载------------");
                System.out.println(download(pa));
            }
        } catch (Exception e) {
            System.out.println("下载失败,请重试");
        }
    }

    /**
     * 下载文件
     *
     * @param path
     * @return
     */
    public static String download(String path) {
        try {
            Document document = Jsoup.connect(path).header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36").get();
            Elements links = document.select("a[href*=https://unsplash.com/photos/]");
            for (Element link : links) {
                System.out.println("正在下载:" + link.attr("href"));
                i++;
                URL url = new URL(link.attr("href"));
                URLConnection uri = url.openConnection();
                uri.setConnectTimeout(100000);
                uri.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
                uri.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                uri.setRequestProperty("Referer", "http://127.0.0.1/");
                // 设置10秒的相应时间
                uri.setConnectTimeout(100000 * 10000);
                System.out.println("大小:0" + getSize(uri) + "MB");
                //获取数据流
                InputStream is = uri.getInputStream();
                //写入数据流
                OutputStream os = new FileOutputStream(new File("C:\\Users\\Jose\\Desktop\\f", i + ".jpg"));
                byte[] buf = new byte[10240000];
                int l = 0;
                int num = 0;
                while ((l = is.read(buf)) != -1) {
                    File file = new File("C:\\Users\\Jose\\Desktop\\f", i + ".jpg");

                    if (num % 30 == 0) {
                        //已下载
                        double size = file.length() / 1024.0 / 1024.0;
                        //文件总大小
                        double allSize = Double.parseDouble(getSize(uri));
                        System.out.println("已下载:" + getDown(size / allSize) + "%");
                    }
                    os.write(buf, 0, l);
                    num++;
                }
                System.out.println(i + ".jpg下载完成.");
                os.close();
                is.close();

                String mm = find("C:\\Users\\Jose\\Desktop\\f\\" + i + ".jpg");
                boolean flag = false;
                for (int a = 0; a < lists.size(); a++) {
                    if (mm.equals(lists.get(a))) {
                        System.gc();//垃圾处理
                        Thread.sleep(1000);
                        File file = new File("C:\\Users\\Jose\\Desktop\\f\\" + i + ".jpg");
                        boolean fl = file.delete();
                        System.out.println("删除" + fl);
                        flag = true;
                        return "下载成功";
                    }
                }
                if (!flag) {
                    lists.add(mm);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "下载完成";
    }

    /**
     * 获取下载文件大小
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getSize(URLConnection url) throws IOException {
        //打开连接
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String d = df.format(url.getContentLength() / 1024.0 / 1024.0);
        return d;
    }

    /**
     * 获取已下文件的百分比值
     *
     * @param a
     * @return
     */
    public static String getDown(double a) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String d = df.format(a * 100);
        return d;
    }

    /**
     * 查询md5值
     *
     * @param path
     * @return md5
     */
    public static String find(String path) {
        String s = "";
        try {
            s = DigestUtils.md5Hex(new FileInputStream(path));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}

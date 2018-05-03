package com.test;

import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author:JoseZ
 * @Description:
 * @Date:Created in 2018/5/2 19:22
 * @Modified By:
 */
public class Test {
    public static List<String> lists = new ArrayList<String>();
    public static Scanner input = new Scanner(System.in);
    public static int i = 0;
    static TextField tx = null;
    static TextField tx1 = null;

    public static void main(String[] args) {
        Frame frame = new Frame("Test");
        frame.setSize(400, 200);
        frame.setLocation(500, 50);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\ijFile\\jsoup\\src\\main\\java\\com\\test\\s.jpg"));
        Button b1 = new Button("Submit");
        tx = new TextField(30);
        frame.add(tx);
        frame.add(b1);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        tx1 = new TextField(50);
        frame.add(tx1);
        b1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] paths = tx.getText().split(",");
                for (String pa : paths) {
//                    System.out.println(download(pa));
                }
            }
        });
        frame.setLayout(new FlowLayout());
        frame.setVisible(true);
    }


//    private static String download(String path) {
//        try {
//            Document document = Jsoup.connect(path).get();
//            Elements links = document.select("a[href*=https://unsplash.com/photos/]");
//
//            for (Element link : links) {
//                System.out.println("link:" + link.attr("href"));
//                System.out.println("text:" + link.text());
//                i++;
//                URL url = new URL(link.attr("href"));
//                URLConnection uri = url.openConnection();
//                //获取数据流
//                InputStream is = uri.getInputStream();
//                //写入数据流
//                OutputStream os = new FileOutputStream(new File("C:\\Users\\Jose\\Desktop\\f", i + ".jpg"));
//                byte[] buf = new byte[10240000];
//                int l = 0;
//                while ((l = is.read(buf)) != -1) {
//                    os.write(buf, 0, l);
//                }
//                os.close();
//                is.close();
//
//                String mm = find("C:\\Users\\Jose\\Desktop\\f\\" + i + ".jpg");
//                boolean flag = false;
//                for (int a = 0; a < lists.size(); a++) {
//                    if (mm.equals(lists.get(a))) {
//                        System.gc();//垃圾处理
//                        Thread.sleep(1000);
//                        File file = new File("C:\\Users\\Jose\\Desktop\\f\\" + i + ".jpg");
//                        boolean fl = file.delete();
//                        System.out.println("删除" + fl);
//                        flag = true;
//                        return "下载成功";
//                    }
//                }
//                if (!flag) {
//                    lists.add(mm);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        return "下载完成";
//    }


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

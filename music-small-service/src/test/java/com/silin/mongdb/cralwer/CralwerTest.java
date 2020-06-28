package com.silin.mongdb.cralwer;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Create by silin
 * Date:  2020/3/7 11:09
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class CralwerTest {


    @Test
    public void cralwer() throws IOException {

        String wwwpath = "https://audio.22family.com//";

        Document doc = Jsoup.connect("https://www.22family.com/music/test/audio?type=0&p=1").ignoreContentType(true)
                .userAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 11_0 like Mac OS X) AppleWebKit/604.1.38 (KHTML, like Gecko) Version/11.0 Mobile/15A372 Safari/604.1 wechatdevtools/1.02.1910120 MicroMessenger/7.0.4 Language/zh_CN webview/").timeout(5000).get();


        String docTxt = doc.body().text();

        JSONObject docJsonObject = JSONUtil.parseObj(docTxt);
        //TODO 下载为json文件

        JSONObject docDataJsonObject = docJsonObject.getJSONObject("data");
        JSONArray audiosJsonArray = docDataJsonObject.getJSONArray("audios");

        for (int i = 0; i < audiosJsonArray.size(); i++) {

            JSONObject coverAudiosJsonArray = audiosJsonArray.getJSONObject(i);
            JSONObject singerStr = coverAudiosJsonArray.getJSONObject("singer");

            String id = coverAudiosJsonArray.getStr("id");
            String singer_id = coverAudiosJsonArray.getStr("singer_id");
            String name = coverAudiosJsonArray.getStr("name");
            String type = coverAudiosJsonArray.getStr("type");
            String created = coverAudiosJsonArray.getStr("created");
            String date = coverAudiosJsonArray.getStr("date");
            String cover = coverAudiosJsonArray.getStr("cover"); // audio/cover/1553691844745097724
            String url = coverAudiosJsonArray.getStr("url");

            String avatar = singerStr.getStr("avatar");
            String singername = singerStr.getStr("name");

            System.out.println(id);
            System.out.println(wwwpath + cover);
            System.out.println(wwwpath + url);
            System.out.println(wwwpath + avatar);
            System.out.println("");

        }

    }

    private String name;



    @Test
    public void select()throws Exception{

        name = "Tony";
        System.out.println(name);
        new Thread(){
            @Override
            public void run() {
//                try {
                    name = "jos";
//                    Thread.sleep(50);
                    System.out.println("center");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            };
        }.start();


        System.out.println("121" + name);
//        Thread.sleep(50);
        System.out.println(name);
    }

}



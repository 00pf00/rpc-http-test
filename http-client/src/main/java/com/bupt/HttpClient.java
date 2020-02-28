package com.bupt;


import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpClient {

    public static void main(String[] args){
         OkHttpClient ht =   new OkHttpClient();
         Request req = new Request.Builder().url("http://127.0.0.1:8080/device").method("GET",null).build();
         Call call = ht.newCall(req);
        try {
            Response resp = call.execute();
            System.out.println(resp.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

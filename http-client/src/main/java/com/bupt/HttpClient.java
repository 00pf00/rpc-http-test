package com.bupt;


import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpClient {

    public static void main(final String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        OkHttpClient ht = new OkHttpClient();
        Request req = new Request.Builder().url("http://127.0.0.1:8080/device").method("GET", null).build();
        Call call = ht.newCall(req);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){
                        System.out.println("请求返回!");
                    }
                    countDownLatch.countDown();
            }
        });
        System.out.println("异步回调!");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程结束!");
        System.exit(1);
    }
}

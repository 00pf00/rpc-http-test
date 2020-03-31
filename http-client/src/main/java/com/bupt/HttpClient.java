package com.bupt;


import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

public class HttpClient {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void main(final String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Integer tenantId = 70;
        OkHttpClient ht = new OkHttpClient();
//        Request req = new Request.Builder().url("http://127.0.0.1:8080/device").method("GET", null).build();
        JSONObject jobj = new JSONObject();

//      jobj.put("id", UUID.randomUUID().toString());
        jobj.put("name", UUID.randomUUID().toString());
        jobj.put("tenantId",tenantId);
//        DeviceInfoProto.DeviceInfo.Builder builder = DeviceInfoProto.DeviceInfo.newBuilder();
//        builder.setId("b");
//        builder.setName("B");
//        DeviceInfoProto.DeviceInfo reqs = builder.build();
//        JSONObject jobj = JSONObject.;
//        System.out.println(reqs);
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jobj.toJSONString());
        Request req = new Request.Builder().url("http://127.0.0.1:8100/api/v1/info/saveDevice").method("POST", body).build();
        Call call = ht.newCall(req);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println(response.body().string());
                    System.out.println("step 1 : 请求返回!");
                }
                countDownLatch.countDown();
            }
        });
        OkHttpClient rht = new OkHttpClient();
        Request rreq = new Request.Builder().url("http://127.0.0.1:8100/api/v1/info/allDevice/"+tenantId).method("GET",null ).build();
        Call rcall = rht.newCall(rreq);
        rcall.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println(response.body().string());
                    System.out.println("step 2 : 请求返回!");
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

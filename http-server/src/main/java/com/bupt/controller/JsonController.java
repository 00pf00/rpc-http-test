package com.bupt.controller;

import com.alibaba.fastjson.JSON;
import com.bupt.entity.Device;
import com.bupt.entity.DeviceInfoProto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonController {

    @RequestMapping(value = "/save", produces = "application/json;charset=UTF-8")
    public DeviceInfoProto.DeviceInfo saveDevice(@RequestBody DeviceInfoProto.DeviceInfo device) {
        System.out.println(device.getId());
        DeviceInfoProto.DeviceInfo.Builder builder = DeviceInfoProto.DeviceInfo.newBuilder();
        builder.setId("a");
        builder.setName("A");
        return builder.build();
    }

    @RequestMapping(value = "/json", produces = "application/json;charset=UTF-8")
    public String getJson(@RequestBody String device) {
        Device d = JSON.parseObject(device, Device.class);
        System.out.println(d.getId());
        Device rd = new Device();
        rd.setId("r");
        rd.setName("R");
        return JSON.toJSONString(rd);
    }
}

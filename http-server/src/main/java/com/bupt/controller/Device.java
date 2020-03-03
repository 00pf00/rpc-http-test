package com.bupt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Device {
    @GetMapping("/device")
    @ResponseBody
    public String GetDevice(){
        try {
            Thread.sleep(7*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "abcd";
    }
}

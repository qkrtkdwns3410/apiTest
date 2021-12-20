package com.example.api.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/*

 */
@RestController
@Log4j2
public class ApiRestController {
    @RequestMapping("/restTest")
    public String restTest(@RequestParam Integer count) {

        String baseUrl = "https://api.bithumb.com/public/orderbook/BTC_KRW";
        String requestParam = "?"+count;
        if (count > 30) {
            requestParam = "?30";
        }
        HashMap<String, Object> result = new HashMap<String, Object>();
        String jsonInString = "";

        try {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

            factory.setConnectTimeout(1000); //타임아웃 설정 5초
            factory.setReadTimeout(1000); // 타임아웃 설정 5초

            
        } catch (Exception e) {

        }
        return "";
    }
}


























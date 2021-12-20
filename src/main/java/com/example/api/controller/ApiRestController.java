package com.example.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/*

 */
@RestController
@Log4j2
public class ApiRestController {
    @RequestMapping("/restTest")
    public String restTest(@RequestParam(defaultValue = "10") Integer count) {


        HashMap<String, Object> result = new HashMap<String, Object>();
        String jsonInString = "";

        try {
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

            factory.setConnectTimeout(1000); //타임아웃 설정 5초
            factory.setReadTimeout(1000); // 타임아웃 설정 5초
            RestTemplate restTemplate = new RestTemplate(factory);

            HttpHeaders headers = new HttpHeaders();
            HttpEntity<?> entity = new HttpEntity<>(headers);

            String baseUrl = "https://api.bithumb.com/public/orderbook/BTC_KRW";
            String requestParam = "?" + count;

            if (count > 30) {
                requestParam = "?count=30";
            }

            // API를 호출하여 MAP타입으로 전달받습니다.
            ResponseEntity<Map> resultMap = restTemplate.exchange(baseUrl.toString(), HttpMethod.GET, entity, Map.class);

            result.put("statusCode", resultMap.getStatusCodeValue()); // http status code를 확인합니다
            result.put("header", resultMap.getHeaders()); // 헤더 정보 확인
            result.put("body", resultMap.getBody()); // 실제 데이터 정보의 확인

            //데이터를 제대로 전달 받았는지 체크 String 형태의 파싱
            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(resultMap.getBody());

        }  catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("dfdfdfdf");
            System.out.println(e.toString());

        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion오류");
            System.out.println(e.toString());
        }

        return jsonInString;
    }
}


























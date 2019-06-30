package com.zack.zzweather.controller;

import com.zack.zzweather.vo.ResponseVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class LocationController {


    @GetMapping(path = "/city/list")
    @ResponseBody
    public ResponseEntity<ResponseVO<List<String>>> findAll() {
        return new ResponseEntity<ResponseVO<List<String>>>(
                new ResponseVO<>(100,
                        "success",
                        Arrays.asList("Sydney","Melbourne","Wollongong")
                ),
                HttpStatus.OK
        );
    }

}
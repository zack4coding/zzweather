package com.zack.zzweather.controller;

import com.zack.zzweather.service.api.ZZLocationServiceApi;
import com.zack.zzweather.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LocationController {

    /**
     *   The supported locations API supplied by the service.
      */
    @Autowired
    private ZZLocationServiceApi locationServiceApi;

    /**
     * Th RESTful API of get all supported city list.
     * @return
     */
    @CrossOrigin
    @GetMapping(path = "/city/list")
    @ResponseBody
    public ResponseEntity<ResponseVO<List<String>>> findAll() {
        return new ResponseEntity<ResponseVO<List<String>>>(
                new ResponseVO<>(100,
                        "success",
                        locationServiceApi.supportedLocations()
                ),
                HttpStatus.OK
        );
    }

}
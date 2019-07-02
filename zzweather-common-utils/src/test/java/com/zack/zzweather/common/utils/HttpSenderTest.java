package com.zack.zzweather.common.utils;

import org.junit.Test;
import org.springframework.test.web.reactive.server.JsonPathAssertions;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HttpSenderTest {

    @Test
    public void doGet() {
        Map params = new HashMap();
        params.put("postal_code", "30000");
        params.put("country", "AU");
        params.put("key", "212a258a95b84252bedc97d973b97bbf");
        String response = HttpSender.getInstance().doGet(
                "https://api.weatherbit.io/v2.0/current",
                params
        );
        System.out.println(response);
        assertNotNull(response);

    }
}
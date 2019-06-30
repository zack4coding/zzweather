package com.zack.zzweather;

import com.zack.zzweather.service.api.dto.WeatherDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZZWeatherApplicationTests {

	@Autowired
	private WebTestClient client;


	@Test
	public void testCurrentWeather() {


		List<WeatherDTO> result = this.client.get()
				.uri("/current/event")
				.accept(TEXT_EVENT_STREAM)
				.exchange()
				.expectStatus().isOk()
				.returnResult(WeatherDTO.class)
				.getResponseBody()
				.take(2) // take 3 comment objects
				.collectList()
				.block();

		result.forEach(x -> System.out.println(x));

		assertEquals(2, result.size());
	}



    @Test
    public void testCityList() {

        client.get().uri("/city/list")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody()
                .jsonPath("$.code").isEqualTo(100)
                .jsonPath("$.message").isEqualTo("success")
                .jsonPath("$.data").isArray()
        ;
    }

}

package com.digest.dertyu.Service;

import com.digest.dertyu.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    private static final String apiKey="d988585a2a204005103c8383bde14add";

    private static final String API="http://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city){
        //json to pojo deseraial
        //pojo to json seriakl
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("key","value");
        String finalApi=API.replace("CITY",city).replace("API_KEY",apiKey);
//        String requestBody="{" +
//                "\"username\":\"raka\",\n"+
//                "\"password\":\"raka\",\n"+
//                "}";

        User user = (User) User.builder().username("raka").password("raka").build();
        HttpEntity<User>httpEntity=new HttpEntity<>(user,httpHeaders);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalApi, HttpMethod.POST, httpEntity, WeatherResponse.class);
        response.getStatusCode();
        return response.getBody();
    }
}

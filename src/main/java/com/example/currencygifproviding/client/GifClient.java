package com.example.currencygifproviding.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${client.gif.url}", name = "gif")
public interface GifClient {

    @GetMapping(value = "?limit=1&lang=en", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getGifFromCuurencyMovement(
            @RequestParam(name = "api_key") String appId,
            @RequestParam(name = "q") String q,
            @RequestParam(name = "offset") int offset,
            @RequestParam(name = "rating") String rating);

    @GetMapping(value = "?api_key=${client.gif.app_id}&limit=1&lang=en", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getGifFromCuurencyMovement(
            @RequestParam(name = "q") String q,
            @RequestParam(name = "offset") int offset,
            @RequestParam(name = "rating") String rating);

}

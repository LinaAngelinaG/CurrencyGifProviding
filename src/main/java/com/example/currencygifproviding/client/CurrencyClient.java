package com.example.currencygifproviding.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(url = "${client.currency.url}", name = "currency")
public interface CurrencyClient {

    @GetMapping(value = "/{dateFilePath}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getPrice(@PathVariable String dateFilePath, @RequestParam(name = "app_id") String appId);

    @GetMapping(value = "/{dateFilePath}?app_id=${client.currency.app_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getPrice(@PathVariable String dateFilePath);

}

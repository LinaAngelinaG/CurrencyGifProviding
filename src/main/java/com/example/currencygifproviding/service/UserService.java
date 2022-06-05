package com.example.currencygifproviding.service;

import com.example.currencygifproviding.client.CurrencyClient;
import com.example.currencygifproviding.client.GifClient;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CurrencyClient currencyClient;
    private final GifClient gifClient;
    private final String[] rating = {"g", "pg", "pg-13", "r"};
    private final int gifOffsetMax = 400;

    public String isGreaterThanYesterday(String currencyName) {
//        check that curName is valid ...
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneOffset.UTC);
            Gson gson = new Gson();
            JSONObject json = new JSONObject(
                    gson.toJson(
                            currencyClient.getPrice(
                                    dtf.format(ZonedDateTime.now()) + ".json")));
            double priceNow = FromJSON.getPrice(json, currencyName);
            json = new JSONObject(
                    gson.toJson(
                            currencyClient.getPrice(
                                    dtf.format(ZonedDateTime.now().plusDays(-1)).toString() + ".json")));
            double priceTomorrow = FromJSON.getPrice(json, currencyName);
            String q = Double.valueOf(priceNow) - Double.valueOf(priceTomorrow) >= 0 ? "rich" : "broke";
            int rand = (int)(Math.random()*gifOffsetMax);
            json = new JSONObject(gson.toJson(gifClient.getGifFromCuurencyMovement(q, rand, rating[rand % rating.length])));
            return FromJSON.getURL(json);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}

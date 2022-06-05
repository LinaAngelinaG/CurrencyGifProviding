package com.example.currencygifproviding.service;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public interface FromJSON {
    public static String getURL(JSONObject jsonObject){
        try {
            jsonObject = jsonObject.getJSONArray("data").getJSONObject(0).getJSONObject("images").getJSONObject("original");
            return  jsonObject.getString("url");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static double getPrice(JSONObject jsonObject, String currencyName){
        try {
            jsonObject = jsonObject.getJSONObject("rates");
            return Double.parseDouble(jsonObject.getString(currencyName));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}

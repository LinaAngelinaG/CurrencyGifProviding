package com.example.currencygifproviding;

import com.example.currencygifproviding.client.CurrencyClient;
import com.example.currencygifproviding.client.GifClient;
import com.example.currencygifproviding.controller.UserController;
import com.example.currencygifproviding.model.SampleResponse;
import com.example.currencygifproviding.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class CurrencyGifProvidingApplicationTests {
    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;

    @MockBean
    private CurrencyClient currencyClient;
    @MockBean
    private GifClient gifClient;

    @MockBean
    private Model model;

    @Test
    public void contextLoads() throws JSONException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        Mockito.when(currencyClient.getPrice(
                        dtf.format(LocalDateTime.now().plusDays(-1))
                                .toString() + ".json"))
                .thenReturn("4.234");

        Mockito.when(currencyClient.getPrice(
                        dtf.format(LocalDateTime.now())
                                .toString() + ".json"))
                .thenReturn("4.467");
        JSONObject jsonObject = new JSONObject("rich");

        Mockito.when(gifClient.getGifFromCuurencyMovement(eq("rich"),isA(Integer.class),isA(String.class))).thenReturn(jsonObject);
        Mockito.when(gifClient.getGifFromCuurencyMovement(eq("broke"),isA(Integer.class),isA(String.class))).thenReturn("broke");

        userController.getCurrencyPriceChanging("anyCur", model);
        assertEquals("rich",model.getAttribute("gifURL"));

    }

}

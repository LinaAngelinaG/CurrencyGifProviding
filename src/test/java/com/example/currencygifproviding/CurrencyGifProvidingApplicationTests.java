package com.example.currencygifproviding;

import org.json.JSONObject;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import com.example.currencygifproviding.client.CurrencyClient;
import com.example.currencygifproviding.client.GifClient;
import com.example.currencygifproviding.controller.UserController;
import com.example.currencygifproviding.service.UserService;
import org.json.JSONException;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CurrencyGifProvidingApplicationTests {
//    @Autowired
//    private WebApplicationContext webApplicationContext;
    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;

    @MockBean
    private CurrencyClient currencyClient;
    @MockBean
    private GifClient gifClient;

//    @MockBean
//    private Model model;
    @Autowired
    private MockMvc mockMvc;

//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }

    @Test
    public void contextLoads() throws JSONException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        String dateNow = dtf.format(LocalDateTime.now()).toString() + ".json";
        String dateTom = dtf.format(LocalDateTime.now().plusDays(-1)).toString() + ".json";

        given(currencyClient.getPrice(dateNow))
                .willReturn("4.234");
        given(currencyClient.getPrice(dateTom))
                .willReturn("4.234");

        given(gifClient.getGifFromCuurencyMovement(eq("rich"),isA(Integer.class),isA(String.class)))
                .willReturn("http1");
        given(gifClient.getGifFromCuurencyMovement(eq("broke"),isA(Integer.class),isA(String.class)))
                .willReturn("http2");

//        Mockito.when(gifClient.getGifFromCuurencyMovement(eq("rich"),isA(Integer.class),isA(String.class))).thenReturn("http1");
//        Mockito.when(gifClient.getGifFromCuurencyMovement(eq("broke"),isA(Integer.class),isA(String.class))).thenReturn("http2");


        try {


            mockMvc.perform(get("/api/check/price/movement/USD"))
                    .andExpect(status().isOk())
                    .andExpect(model().attribute("gifURL","http1"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


//        Mockito.when(currencyClient.getPrice(
//                                dateNow))
//                .thenReturn("4.234");
//
//        Mockito.when(currencyClient.getPrice(
//                        dateTom))
//                .thenReturn("4.467");
//        JSONObject jsonObject = new JSONObject("rich");
//
//        userController.getCurrencyPriceChanging("anyCur", model);
//        assertEquals("rich",model.getAttribute("gifURL"));

    }



}

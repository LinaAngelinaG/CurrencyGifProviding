package com.example.currencygifproviding.controller;

import com.example.currencygifproviding.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/check/price/movement/{currencyName}")
    public String getCurrencyPriceChanging(@PathVariable String currencyName, Model model){
        model.addAttribute("curName", currencyName);
        model.addAttribute("gifURL", userService.isGreaterThanYesterday(currencyName));
        return "gif";
    }

}

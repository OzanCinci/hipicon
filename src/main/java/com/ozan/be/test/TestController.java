package com.ozan.be.test;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("t")
    public static String test(){
        return "Helloooo there!";
    }

    @GetMapping("z")
    public static String priv(){
        return "PRIV ENDPOINT Helloooo there!";
    }

    @GetMapping("management")
    @PreAuthorize("hasRole('MANAGER')")
    public static String management(){
        return "management::get";
    }
}

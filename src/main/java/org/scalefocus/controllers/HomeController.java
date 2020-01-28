package org.scalefocus.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("")
    public String getHomePage() {
        return "<h1>Welcome!</h1>";
    }

    @GetMapping("users")
    public String getUser() {
        return "<h1>Welcome User</h1>";
    }
}

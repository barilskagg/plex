package org.scalefocus.controllers;

import org.scalefocus.requests.AuthenticationRequest;
import org.scalefocus.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private AuthenticationService loginService;

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequest request) throws Exception {
        String jwtCookieString = loginService.getJwtCookie(request).toString();
        UserDetails userDetails = loginService.getUserDetails(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookieString)
                .body(userDetails);
    }

    @PostMapping("hi")
    public String createAuthenticationTokes2() throws Exception {
        return "Success";
    }

    @GetMapping("index")
    public String indexPage() throws Exception {
        return "Hello";
    }
}

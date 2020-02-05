package org.scalefocus.services;

import org.scalefocus.requests.AuthenticationRequest;
import org.scalefocus.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PlexUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtTokenUtil;

    public HttpCookie getJwtCookie(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseCookie.from("jwt", jwt)
                .path("/")
                .maxAge(3600 * 6)
                .domain("localhost")
                .httpOnly(true)
                .build();
    }

    public UserDetails getUserDetails(AuthenticationRequest request) {
        return userDetailService.loadUserByUsername(request.getUsername());
    }
}

package com.example.lab3;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CookiesService {
    HttpServletRequest reqest;
    HttpServletResponse response;
    Cookie[] cookies;


    public CookiesService(HttpServletRequest reqest, HttpServletResponse response) {
        this.reqest = reqest;
        cookies = reqest.getCookies();
        this.response = response;
    }

    public CookiesService(HttpServletResponse response) {
        this.response = response;
    }

    public CookiesService(HttpServletRequest reqest) {
        this.reqest = reqest;
        cookies = reqest.getCookies();
    }

    public String findCookieValue(String key) {
        Cookie[] cookies = reqest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    public Cookie addCookie(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(2880);
        response.addCookie(cookie);
        return cookie;
    }

    public void addAllCookie(Map<String, Double> map) {
        for (String name : map.keySet()) {
            addCookie(name, String.valueOf(map.get(name)));
        }
    }
}

package com.example.lab2.param.processing;

import com.example.lab2.validate.IValidator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ParamsServletContainer<T> {
    void setParams(HttpServletRequest request);

     void addCookies(HttpServletResponse response);
}

package com.example._20220521springsecuritytest1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SController {
@GetMapping()
    public String getPage(){
    return "loginPage";
}
}

package com.wondersgroup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenlin
 * @create 2019-07-04 16:40
 * @description: TODO
 * @version：1.0
 **/

@Controller
public class MainController {


    @RequestMapping(value = {"", "/", "/main"})
    public String main() {
        return "main";
    }
}

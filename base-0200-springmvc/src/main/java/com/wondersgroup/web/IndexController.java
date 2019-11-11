package com.wondersgroup.web;

import com.wondersgroup.dto.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlin
 * @create 2019-05-28 12:24
 * @description: 测试首页controller
 * @version：1.0
 **/
@Controller
public class IndexController {

    private List<UserDto> list;

    public IndexController() {
        list = new ArrayList<>();
        UserDto dto = null;
        for (int i = 0; i < 10; i++) {
            dto = new UserDto();
            dto.setId(new Long(i));
            dto.setLoginName(i + "-loginName");
            dto.setPassword(i + "");
            list.add(dto);

        }

    }


    @GetMapping({"/", "/index"})
    public String index(Model model) {
        System.out.println("index");
        model.addAttribute("list", list);
        return "index";
    }

    @RequestMapping("test")
    @ResponseBody
    public List<UserDto> test() {

        return list;
    }

}

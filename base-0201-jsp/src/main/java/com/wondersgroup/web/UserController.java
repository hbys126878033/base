package com.wondersgroup.web;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenlin
 * @create 2019-05-30 22:14
 * @description: TODO
 * @version：1.0
 **/
@Controller
public class UserController {

    @RequestMapping(value = "/user")
    public String getUsers(Model model) {
        System.out.println("getUsers");
        List<User> list = new ArrayList<>();
        User u1 = new User("1", "AAA", "市场部");
        User u2 = new User("2", "BBB", "研发部");
        User u3 = new User("3", "CCC", "人事部");
        User u4 = new User("4", "DDD", "销售部");
        User u5 = new User("5", "EEE", "市场部");
        User u6 = new User("6", "FFF", "研发部");
        User u7 = new User("7", "GGG", "销售部");
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        list.add(u5);
        list.add(u6);
        list.add(u7);
        model.addAttribute("users", list);
        return "user";

    }
}

@Data
@NoArgsConstructor
class User {

    private String id;
    private String name;
    private String dept;

    public User(String id, String name, String dept) {
        this.id = id;
        this.name = name;
        this.dept = dept;
    }
}

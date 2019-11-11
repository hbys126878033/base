package com.wondersgroup.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author chenlin
 * @create 2019-05-30 16:09
 * @description: TODO
 * @version：1.0
 **/
@Controller
public class UploadController {


    @ResponseBody
    @RequestMapping(value = "upload")
    public String upload(MultipartFile fileName) throws IOException {
        System.out.println(fileName.getOriginalFilename());
        System.out.println(fileName.getSize());
        fileName.transferTo(new File("d:/" + fileName.getOriginalFilename()));
        return "OK";
    }
}

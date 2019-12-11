package com.johnny.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/file")
public class FileUploadController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/index")
    public String fileUpload(Model model) {
        //TODO
        return "fileupload";
    }

    @RequestMapping("/fileUpload")
    @ResponseBody
    public String uploadFile(HttpServletRequest request, MultipartFile uploadFile) {
        try {
            String originalFilename = uploadFile.getOriginalFilename();
            InputStream fileIn = uploadFile.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            int temp = 0;
            while ((temp = fileIn.read()) != -1){
                bytes[len] = (byte) temp;
                len++;
            }
            System.out.println(new String(bytes,0,len));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

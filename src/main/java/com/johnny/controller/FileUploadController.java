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

    @RequestMapping(value = "/fileupload")
    @ResponseBody
    public String uploadFile(HttpServletRequest request, MultipartFile uploadFile) {
        try {
            String originalFilename = uploadFile.getOriginalFilename();
            logger.info("文件名:{}",originalFilename);
            InputStream fileIn = uploadFile.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileIn.read(bytes)) != -1) {
                System.out.println(new String(bytes,0,len));
            }
            fileIn.close();
            return "SUCCESS";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

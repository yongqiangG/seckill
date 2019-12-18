package com.johnny.controller;

import com.johnny.utils.UDPServer;
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
import java.util.HashMap;
import java.util.Map;

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
            logger.info("文件名:{}", originalFilename);
            InputStream fileIn = uploadFile.getInputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fileIn.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
            fileIn.close();
            return "SUCCESS";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/macReset")
    @ResponseBody
    public Map macReset(HttpServletRequest request) {
        Map<String,Object> map = new HashMap<String,Object>();
        String macCode = request.getParameter("macCode");
        String listenPort = request.getParameter("listenPort");
        System.out.println("macCode:" + macCode);
        if (macCode == null || listenPort == null) {
            map.put("success",false);
            map.put("msg","机器码或者端口号不能为空");
            return map;
        }
        try {
            UDPServer.serverStartListen(macCode, Integer.valueOf(listenPort));
        } catch (IOException e) {
            e.printStackTrace();
        }
        map.put("success",true);
        map.put("msg","success");
        return map;
    }

    @RequestMapping(value = "/hardwareIndex")
    public String hardwareIndex(Model model) {
        //TODO
        return "hardwareIndex";
    }
}

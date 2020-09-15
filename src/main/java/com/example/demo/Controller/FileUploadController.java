package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * SpringBoot 文件是传
 */

@RestController //表示该类方法下的返回值会自动做json格式的转换
public class FileUploadController {
    /**
     * 处理文件上传
     */
@RequestMapping("/fileUploadController")
    public Map<String,Object> fileUpload(MultipartFile filename) throws IOException {
        System.out.println(filename.getOriginalFilename());
        String pathname="e:/"+filename.getOriginalFilename();
        System.out.println(pathname);
        try {
            filename.transferTo(new File(pathname));
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String,Object> map=new HashMap<>();
        map.put("msg","OK");
        return map;

    }

}

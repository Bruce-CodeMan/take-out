package com.brucecompiler.controller.admin;

import com.brucecompiler.constant.MessageConstant;
import com.brucecompiler.constant.StatusCodeConstant;
import com.brucecompiler.result.Result;
import com.brucecompiler.utils.OssUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/admin/common")
public class CommonController {

    private final OssUtil ossUtil;

    @Autowired
    public CommonController(OssUtil ossUtil) {
        this.ossUtil = ossUtil;
    }

    @PostMapping("/upload")
    public Result<Object> upload(MultipartFile file) {

        // Get the origin file name
        String originalFilename = file.getOriginalFilename();

        // Get the suffix: .jpg
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String url = null;

        try{
            String objectName = UUID.randomUUID().toString() + suffix;
            url = ossUtil.upload(file.getBytes(), objectName);
        } catch (IOException e) {
            return Result.error(StatusCodeConstant.FAILURE, MessageConstant.UPLOAD_ERROR);
        }
        return Result.success(url);
    }
}

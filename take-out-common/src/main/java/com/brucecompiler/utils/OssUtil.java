package com.brucecompiler.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
@Slf4j
public class OssUtil {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;

    public String upload(byte[] bytes, String objectName) {
        // Create the OssClient object
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);

        try {
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException e) {
            log.error("Caught an OSSException, which means your request made it to OSS");
            log.error("Error Message: " + e.getMessage());
        } finally {
            if(ossClient != null) ossClient.shutdown();
        }

        StringBuilder stringBuilder = new StringBuilder("https://");
        stringBuilder
                .append(bucketName)
                .append(".")
                .append(endpoint)
                .append("/")
                .append(objectName);

        log.info("upload endpoint is: {}", stringBuilder.toString());

        return stringBuilder.toString();
    }
}

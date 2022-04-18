package com.xyy.minio.demo;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yayu
 * @title: UploadFile
 * @description: 文件上传
 * @date 2022/4/18 16:05
 */
@RestController
@RequestMapping("/minio")
public class UploadFile {

    @Autowired
    private MinioClient minioClient;

    private static final String bucketName = "test";

    @RequestMapping("/upload")
    public String upload(@RequestParam(name = "files") MultipartFile[] files) {
        if (files == null || files.length == 0) {
            return "上传文件不能为空";
        }
        List<String> orgfileNameList = new ArrayList<>(files.length);
        for (MultipartFile multipartFile : files) {
            String orgfileName = multipartFile.getOriginalFilename();
            orgfileNameList.add(orgfileName);
            try {
                //文件上传
                InputStream in = multipartFile.getInputStream();
                minioClient.putObject(
                        PutObjectArgs.builder().bucket(bucketName).object(orgfileName).stream(
                                in, multipartFile.getSize(), -1)
                                .contentType(multipartFile.getContentType())
                                .build());
                in.close();
            } catch (Exception e) {
                return "失bai";
            }

        }
        return "ok";
    }


}


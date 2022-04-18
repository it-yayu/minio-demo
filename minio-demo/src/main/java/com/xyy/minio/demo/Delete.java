package com.xyy.minio.demo;

import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yayu
 * @title: Delete
 * @description: minio 删除操作
 * @date 2022/4/18 16:44
 */
@RestController
@RequestMapping("/minio")
public class Delete {
    @Autowired
    private MinioClient minioClient;

    private static final String bucketName = "test";

    @DeleteMapping("/delete/{fileName}")
    public String delete(@PathVariable("fileName") String fileName) {
        try {
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
        } catch (Exception e) {
            return "删除失败";
        }
        return "删除成功";
    }

}

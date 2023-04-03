package com.iotstar.onlinetest.utils;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUtils {

    @Autowired
    Cloudinary getCloudinary;
    public String upload(MultipartFile fileInput, String fileName) {
        try {
            java.io.File file = new java.io.File(System.getProperty("java.io.tmpdir")+"/"+fileName);
            fileInput.transferTo(file);

            getCloudinary.uploader().upload(file, ObjectUtils.asMap("public_id", fileName));
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

// Transform
        String url = getCloudinary.url().generate(fileName);
        return url;
    }
}

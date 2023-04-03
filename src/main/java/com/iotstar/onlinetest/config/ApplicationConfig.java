package com.iotstar.onlinetest.config;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class ApplicationConfig {

    //Use model mapper (Map entity to DTO)
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }


    // use https://cloudinary.com/ to store image
    @Bean
    public Cloudinary getCloudinary (){
        Map config = new HashMap();
        config.put("cloud_name", "dx7nsygei");
        config.put("api_key", "889198864823844");
        config.put("api_secret", "m1llh5b2a2t_1JaApGYaXcjiL3w");
        Cloudinary cloudinary = new Cloudinary(config);
        return cloudinary;
    }
}

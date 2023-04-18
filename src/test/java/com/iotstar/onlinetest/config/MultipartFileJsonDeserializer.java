package com.iotstar.onlinetest.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


public class MultipartFileJsonDeserializer extends JsonDeserializer<MultipartFile> {

    @Override
    public MultipartFile deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // Lấy ra tên file từ đối tượng JSON
        JsonNode node = p.getCodec().readTree(p);
        String fileName = node.get("fileName").asText();

        // Lấy ra nội dung file từ đối tượng JSON
        byte[] fileContent = node.get("fileContent").binaryValue();

        // Tạo đối tượng MultipartFile từ tên file và nội dung file
        MultipartFile multipartFile = new MockMultipartFile(fileName, fileName, null, fileContent);

        return multipartFile;
    }
}
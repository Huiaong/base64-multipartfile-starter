package com.huiaong.base64.multipartfile.starter.multipart;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Base64MultipartFileSerializer extends JsonDeserializer<Base64MultipartFile> {
    private final Pattern BASE64_PATTERN = Pattern.compile("data:(.*/.*);base64,");

    @Override
    public Base64MultipartFile deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        String base64 = jsonParser.getText();
        if (base64 == null || "".equals(base64)) {
            return null;
        }

        Matcher matcher = BASE64_PATTERN.matcher(base64);
        if (!matcher.lookingAt()) {
            return null;
        }

        byte[] b = new BASE64Decoder().decodeBuffer(BASE64_PATTERN.split(base64)[1]);

        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }

        String contentType = matcher.group(1);
        return new Base64MultipartFile(b, contentType, Suffix.getSuffix(contentType));
    }
}

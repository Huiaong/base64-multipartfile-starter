package com.huiaong.base64.multipartfile.starter.multipart;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import sun.misc.BASE64Decoder;

import java.io.IOException;

public class Base64MultipartFileSerializer extends JsonDeserializer<Base64MultipartFile> {
	public final static Base64MultipartFileSerializer instance = new Base64MultipartFileSerializer();


	@Override
	public Base64MultipartFile deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
		String base64 = jsonParser.getText();
		String[] baseStr = base64.split(",");

		byte[] b = new BASE64Decoder().decodeBuffer(baseStr[1]);

		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {
				b[i] += 256;
			}
		}

		return new Base64MultipartFile(b, baseStr[0]);
	}
}

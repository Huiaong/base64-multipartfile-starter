package com.huiaong.base64.multipartfile.starter.multipart;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@JsonDeserialize(using = Base64MultipartFileSerializer.class)
public class Base64MultipartFile implements MultipartFile {
	private final byte[] imgContent;
	private final String name;
	private final String contentType;

	public Base64MultipartFile(byte[] imgContent, String contentType, String suffix) {
		this.imgContent = imgContent;
		this.name = UUID.randomUUID().toString().replace("-", "") + suffix;
		this.contentType = contentType;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getOriginalFilename() {
		return name;
	}

	@Override
	public String getContentType() {
		return contentType;
	}

	@Override
	public boolean isEmpty() {
		return imgContent == null || imgContent.length == 0;
	}

	@Override
	public long getSize() {
		return imgContent.length;
	}

	@Override
	public byte[] getBytes() {
		return imgContent;
	}

	@Override
	public InputStream getInputStream() {
		return new ByteArrayInputStream(imgContent);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		new FileOutputStream(dest).write(imgContent);
	}
}

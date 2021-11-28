package com.huiaong.base64.multipartfile.starter.multipart;

import java.util.Arrays;
import java.util.UnknownFormatConversionException;

public enum Suffix {

    DOC("application/msword", ".doc"),
    DOCX("application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx"),
    XLS("application/vnd.ms-excel", ".xls"),
    XLSX("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".xlsx"),
    PDF("application/pdf", ".pdf"),
    PPT("application/vnd.ms-powerpoint", ".ppt"),
    PPTX("application/vnd.openxmlformats-officedocument.presentationml.presentation", ".pptx"),
    TXT("text/plain", ".txt"),
    PNG("image/png", ".png"),
    JPEG("image/jpeg", ".jpeg"),
    GIF("image/gif", ".gif"),
    SVG("image/svg+xml", ".svg"),
    ICO("image/x-icon", ".ico"),
    BMP("image/bmp", ".bmp"),

    ;

    private final String contentType;
    private final String suffix;

    Suffix(String contentType, String suffix) {
        this.contentType = contentType;
        this.suffix = suffix;
    }

    public static String getSuffix(String contentType) {
        return Arrays.stream(values()).filter(suff -> suff.contentType.equals(contentType)).findAny()
                .orElseThrow(() -> new UnknownFormatConversionException("invalid contentType " + contentType)).suffix;
    }
}

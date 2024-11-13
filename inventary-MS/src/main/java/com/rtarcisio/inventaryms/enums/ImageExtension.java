package com.rtarcisio.inventaryms.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter

public enum ImageExtension {
    PNG("image/png"), GIF("image/gif"), JPEG("image/jpeg");

    private String mediaType;

    ImageExtension(String mediaType) {

        this.mediaType = mediaType;
    }

    public static ImageExtension valueOf_(String mediaType) {
        return Arrays.stream(values()).filter(ie -> ie.mediaType.equals(mediaType)).findFirst().orElse(null);
    }

    public static ImageExtension ofName(String extension) {
        return Arrays.stream(values()).filter(ie -> ie.name().equalsIgnoreCase(extension)).findFirst().orElse(null);
    }
}
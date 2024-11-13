package com.rtarcisio.inventaryms.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDTO {
    private String name;
    private long size;
    private String contentType;
    private byte[] content; // Representa o arquivo como array de bytes, serializável por Jackson



    // Getters e setters
}
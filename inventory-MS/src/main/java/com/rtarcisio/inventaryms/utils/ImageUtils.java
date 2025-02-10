package com.rtarcisio.inventaryms.utils;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImageUtils {

    public static byte[] downloadImageFromFileSystem() throws IOException {
        Path filePath = Paths.get(Constraints.imgSrc);

        // Verifica se o arquivo existe
        if (!Files.exists(filePath)) {
            throw new IOException("File not found: ");
        }

        // Lê o arquivo e retorna como byte array
        return Files.readAllBytes(filePath);
    }
}

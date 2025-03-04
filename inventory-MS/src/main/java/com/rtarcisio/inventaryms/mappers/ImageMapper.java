package com.rtarcisio.inventaryms.mappers;


import com.rtarcisio.inventaryms.domains.ImageProduct;
import com.rtarcisio.inventaryms.dtos.ImageDTO;
import com.rtarcisio.inventaryms.enums.ImageExtension;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImageMapper {

    public static ImageProduct mapToImage(MultipartFile midia) throws IOException {


        return ImageProduct.builder()
                .name(midia.getOriginalFilename())
                .size(midia.getSize())
                .extension(ImageExtension.valueOf_(midia.getContentType().toString()))
                .file(midia.getBytes())
                .build();

    }

    public static List<ImageProduct> mapToImageList(List<MultipartFile> mediaList) {
        return mediaList.stream()
                .map(midia -> {
                    try {
                        if (mediaList.isEmpty())
                            return null;

                        return mapToImage(midia);
                    } catch (IOException e) {
                        throw new RuntimeException("Erro aq");
                    }
                })
                .collect(Collectors.toList());
    }

    public static ImageDTO mapToImageDTO(MultipartFile file) throws IOException {
        return new ImageDTO(
                file.getOriginalFilename(),
                file.getSize(),
                file.getContentType(),
                file.getBytes());
    }

    public static List<ImageDTO> mapToImageDTOList(List<MultipartFile> files) throws IOException {
        return files.stream()
                .map(file -> {
                    try {
                        return mapToImageDTO(file);
                    } catch (IOException e) {
                        throw new RuntimeException("Erro ao mapear MultipartFile para ImageDTO", e);
                    }
                })
                .collect(Collectors.toList());
    }


    public static MultipartFile mapToMultipartFile(ImageProduct imageProduct) throws IOException {
        return new CustomMultipartFile(
                imageProduct.getName(),
                imageProduct.getFile(),
                imageProduct.getExtension().getMediaType(),
                imageProduct.getSize());
    }

//    public static MultipartFile mapToMultipartFileDefault(byte[] img) {
//        return new CustomMultipartFile(
//                "imageProduct.getName()",
//                img,
//                "imageProduct.getExtension().getMediaType()",
//                100L);
//    }


    public static List<MultipartFile> mapToMultipartFileList(List<ImageProduct> imageProductList) {
        return imageProductList.stream()
                .map(imageProduct -> new CustomMultipartFile(
                        imageProduct.getName(),
                        imageProduct.getFile(),
                        imageProduct.getExtension().getMediaType(),
                        imageProduct.getSize()))
                .collect(Collectors.toList());
    }

//	public static MultipartFile mapToMultipartFile(Foto foto) throws IOException {
//		return new CustomMultipartFile(
//				foto.getName(),
//				foto.getFile(),
//				foto.getExtension().getMediaType(),
//				foto.getSize());
//	}

    public static ImageDTO createDefaultImage() throws IOException {
        byte[] img = downloadImageFromFileSystem();
        return new ImageDTO(
                "default-image.png",
                img.length,
                "image/png",
                img
        );
    }

    public static byte[] downloadImageFromFileSystem() {
        try {
            // Usa o ClassLoader para encontrar o recurso no caminho correto em tempo de execução
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader.getResource("images/no-image.png") == null) {
                throw new IOException("File not found: images/no-image.png");
            }

            Path filePath = Paths.get(classLoader.getResource("images/no-image.png").toURI());
            return Files.readAllBytes(filePath);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao carregar a imagem", e);
        }
    }

    private static class CustomMultipartFile implements MultipartFile {

        private final String name;
        private final byte[] content;
        private final String contentType;
        private final long size;

        public CustomMultipartFile(String name, byte[] content, String contentType, long size) {
            this.name = name;
            this.content = content;
            this.contentType = contentType;
            this.size = size;
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
            return content.length == 0;
        }

        @Override
        public long getSize() {
            return size;
        }

        @Override
        public byte[] getBytes() throws IOException {
            return content;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(content);
        }

        @Override
        public void transferTo(File dest) throws IOException, IllegalStateException {
        }
    }
}

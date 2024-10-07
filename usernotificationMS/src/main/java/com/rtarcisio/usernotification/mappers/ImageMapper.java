package com.rtarcisio.usernotification.mappers;

import com.rtarcisio.usernotification.domains.Foto;
import com.rtarcisio.usernotification.enums.ImageExtension;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Component
public class ImageMapper {

	public static Foto mapToImage(MultipartFile midia) throws IOException {

		//ImageExtension extensao = ImageExtension.valueOf(midia.getContentType().toString());

		return Foto.builder()
				.name(midia.getOriginalFilename())
				.size(midia.getSize())
				.extension(ImageExtension.valueOf_(midia.getContentType().toString()))
				.file(midia.getBytes())
				.build();

	}

	public static MultipartFile mapToMultipartFile(Foto foto) throws IOException {
		return new CustomMultipartFile(
				foto.getName(),
				foto.getFile(),
				foto.getExtension().getMediaType(),
				foto.getSize());
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
			// Implementar se necessário
		}
	}
}

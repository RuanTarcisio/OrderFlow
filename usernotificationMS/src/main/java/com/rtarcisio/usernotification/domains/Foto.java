package com.rtarcisio.usernotification.domains;

import com.rtarcisio.usernotification.enums.ImageExtension;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "fotos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private Long size;

    @Enumerated(EnumType.STRING)
    @Column(name = "extense")
    private ImageExtension extension;

    @CreatedDate
    private LocalDateTime uploadDate;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] file;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

//    public String getFileName() {
//
//        return getName().concat(".").concat(getExtension().name());
//    }

//    public enum ImageExtension {
//        PNG(MediaType.IMAGE_PNG), GIF(MediaType.IMAGE_GIF), JPEG(MediaType.IMAGE_JPEG);
//
//        @Getter
//        private MediaType mediaType;
//
//        ImageExtension(MediaType mediaType) {
//
//            this.mediaType = mediaType;
//        }
//
//        public static ImageExtension valueOf(MediaType mediaType) {
//            return Arrays.stream(values()).filter(ie -> ie.mediaType.equals(mediaType)).findFirst().orElse(null);
//        }
//
//        public static ImageExtension ofName(String extension) {
//            return Arrays.stream(values()).filter(ie -> ie.name().equalsIgnoreCase(extension)).findFirst().orElse(null);
//        }
//    }
}

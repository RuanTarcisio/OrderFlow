package com.rtarcisio.inventaryms.domains;

import com.rtarcisio.inventaryms.enums.ImageExtension;
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
@Table(name = "image_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageProduct {

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

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}

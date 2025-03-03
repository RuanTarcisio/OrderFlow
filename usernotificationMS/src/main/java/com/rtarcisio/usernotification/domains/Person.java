package com.rtarcisio.usernotification.domains;

import com.rtarcisio.usernotification.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotEmpty
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String passwd;

    private String cpf;

    private String name;

    private LocalDate dataNascimento;

    @Embedded
    private Address address;

    @ElementCollection
    @CollectionTable(
            name = "products_followed",
            joinColumns = @JoinColumn(name = "user_id")
    )
    @Column(name = "skuProduct_id")
    private Set<String> productsFollowed = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;
}

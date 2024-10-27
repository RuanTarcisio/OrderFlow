package com.rtarcisio.usernotification.domains;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Embeddable
@Data
@AllArgsConstructor
public class Address {

    private String country;
    private String state;
    private String city;
    private String road;
    private String cep;
    private Integer number;


}

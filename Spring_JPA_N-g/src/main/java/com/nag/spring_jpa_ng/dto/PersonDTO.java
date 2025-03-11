package com.nag.spring_jpa_ng.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String phoneNo;
    private String email;
    private String firstName;
    private String lastName;
    private byte profilePic;

}

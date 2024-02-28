package org.example.eco.user.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBaseDto {
    private String fName;
    private String lName;
    private String phoneNumber;
    private String email;
}

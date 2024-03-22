package org.example.eco.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto extends UserBaseDto {
    private String address;

    private String city;

    private String past_code;

    private String country;

    private String region;

    private String password;

    private String conformPassword;

}

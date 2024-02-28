package org.example.eco.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatePhoneNumberRequestDto {
    @NotBlank
    @Pattern(regexp = "^998[0-9]{9}$")
    @Column(nullable = false)
    private String phoneNumber;
    private Integer otp;
}

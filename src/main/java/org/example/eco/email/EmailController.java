package org.example.eco.email;


import lombok.RequiredArgsConstructor;
import org.example.eco.email.dto.EmailDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.example.eco.email.EmailController.BATH_USER;

@RestController
@RequestMapping( BATH_USER)
@RequiredArgsConstructor
public class EmailController {

    public static final String BATH_USER = "/email";

    private final EmailService emailService;
    @PostMapping("/auth/verification")
    public ResponseEntity<?> getVerification(@RequestBody EmailDto email){
        String verified = emailService.verified(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(verified);
    }

    @GetMapping("/auth/reTry")
    public ResponseEntity<?> reTryEmailVer(@RequestBody EmailDto emailDto){
         emailService.retryEmilVer(emailDto);
         return ResponseEntity.status(HttpStatus.OK).build();
    }


}
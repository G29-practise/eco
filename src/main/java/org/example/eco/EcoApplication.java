package org.example.eco;

import lombok.RequiredArgsConstructor;
import org.example.eco.user.UserRepository;
import org.example.eco.user.entity.User;
import org.example.eco.user.role.RoleRepository;
import org.example.eco.user.role.entity.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
@RequiredArgsConstructor
public class EcoApplication implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(EcoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        createAdmin();
    }

    private void createAdmin(){
        String phoneNumber = "998333272322";
        String email = "admin@gmail.com";
        Set< Role > roles = Collections.singleton(roleRepository.findByName("ADMIN").orElseThrow());
        User user = new User(UUID.randomUUID(),
                "Admin",
                "Admin",
                phoneNumber,
                email,
                LocalDateTime.now(),
                LocalDateTime.now(),
                null,
                passwordEncoder.encode("admin"),
                true,
                roles,
                Collections.emptySet(), null, null);
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isEmpty()){
            userRepository.save(user);
        }
    }
}

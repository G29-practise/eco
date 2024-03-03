package org.example.eco.user;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.exceptions.OtpException;
import org.example.eco.common.service.GenericService;
import org.example.eco.user.dto.UserCreateDto;
import org.example.eco.user.dto.UserResponseDto;
import org.example.eco.user.dto.UserSignInDto;
import org.example.eco.user.dto.UserUpdateDto;
import org.example.eco.user.entity.User;
import org.example.eco.user.otp.OtpRepository;
import org.example.eco.user.otp.entity.Otp;
import org.example.eco.user.role.RoleRepository;
import org.example.eco.user.role.entity.Role;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class UserService extends GenericService<User, UUID, UserCreateDto, UserResponseDto, UserUpdateDto> {
    private final UserRepository repository;
    private final Class<User> entityClass = User.class;
    private final UserDtoMapper mapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;

    @Override
    protected UserResponseDto internalCreate(UserCreateDto userCreateDto) {
        User user = mapper.toEntity(userCreateDto);
        user.setId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Set<Role> roles = Collections.singleton(roleRepository.findByName("ADMIN").orElseThrow());
        user.setRoles(roles);

        isPhoneNumberVerified(userCreateDto.getPhoneNumber());

        User saved = repository.save(user);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected UserResponseDto internalUpdate(UUID uuid, UserUpdateDto userUpdateDto) {
        return null;
    }

    private void isPhoneNumberVerified(String phoneNumber) {
        Otp otp = otpRepository
                .findById(phoneNumber)
                .orElseThrow(() -> new OtpException.PhoneNumberNotVerified(phoneNumber));

        if (!otp.isVerified()) {
            throw new OtpException.PhoneNumberNotVerified(phoneNumber);
        }
    }

    @Transactional
    public UserResponseDto signIn(UserSignInDto signInDto) {
        User user = repository.findByPhoneNumber(signInDto.getPhoneNumber())
                .orElseThrow(() -> new BadCredentialsException("Username or password is not correct"));

        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Username or password is not correct");
        }

        return mapper.toResponseDto(user);
    }
}

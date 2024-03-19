package org.example.eco.user;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.exceptions.OtpException;
import org.example.eco.common.exceptions.WrongInputException;
import org.example.eco.common.service.GenericService;
import org.example.eco.email.dto.EmailDto;
import org.example.eco.user.dto.UserCreateDto;
import org.example.eco.user.dto.UserResponseDto;
import org.example.eco.user.dto.UserSignInDto;
import org.example.eco.user.dto.UserUpdateDto;
import org.example.eco.user.entity.User;
import org.example.eco.user.otp.OtpRepository;
import org.example.eco.user.otp.entity.Otp;
import org.example.eco.user.role.RoleRepository;
import org.example.eco.validation.UserValidation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.example.eco.common.ExcMessage.*;

@Service
@Getter
@RequiredArgsConstructor
public class UserService extends GenericService<User, UUID, UserCreateDto, UserResponseDto, UserUpdateDto> implements UserDetailsService {
    private final UserRepository repository;
    private final Class<User> entityClass = User.class;
    private final UserDtoMapper mapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;
    private final NotificationService notificationService;
    private final Random random = new Random();
    private final RedisTemplate<String, EmailDto> redisTemplate;
    private final UserValidation validation;

    @Override
    protected UserResponseDto internalCreate(UserCreateDto user) {

        if (!validation.isValidPassword(user.getPassword())) {
            throw new WrongInputException(INVALID_PASSWORD);
        }
        if (!validation.isValidPhoneNumber(user.getPhoneNumber())) {
            throw new WrongInputException(INVALID_PHONE_NUMBER);
        }
        if (!validation.isValidEmail(user.getEmail())) {
            throw new WrongInputException(INVALID_EMIL);
        }

        int code = random.nextInt(1000, 10000);
        notificationService.sendVerifyCode(user.getEmail(), code);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        EmailDto emailDto = new EmailDto(user.getEmail(), String.valueOf(code),user);
        redisTemplate.opsForValue().set(user.getEmail(), emailDto, 5, TimeUnit.MINUTES);
        return mapper.userResponseDto(user);
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
        User user = repository.findByEmail(signInDto.getEmail())
                .orElseThrow(() -> new BadCredentialsException("Username or password is not correct"));

        if (!passwordEncoder.matches(signInDto.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Username or password is not correct");
        }

        return mapper.toResponseDto(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException(BAD_CREDENTIALS));
    }
}

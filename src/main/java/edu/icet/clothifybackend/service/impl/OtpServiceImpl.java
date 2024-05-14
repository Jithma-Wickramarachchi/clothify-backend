package edu.icet.clothifybackend.service.impl;

import edu.icet.clothifybackend.dto.MailBody;
import edu.icet.clothifybackend.entity.OtpEntity;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.exception.otp.OtpHasBeenExpiredException;
import edu.icet.clothifybackend.exception.otp.OtpIsNotCorrectException;
import edu.icet.clothifybackend.exception.otp.OtpNotFoundException;
import edu.icet.clothifybackend.exception.user.UserNotFoundException;
import edu.icet.clothifybackend.repository.OtpRepository;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.security.AuthenticationResponse;
import edu.icet.clothifybackend.service.EmailService;
import edu.icet.clothifybackend.service.JwtService;
import edu.icet.clothifybackend.service.OtpService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final Random random;

    @Override
    public void sendOtp(String username) {
        //Check whether user is available
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        //Generate otp and include into email
        Integer otp = generateOtp();
        MailBody mailBody = MailBody.builder()
                .to(username)
                .text("Your OTP number is " + otp + " . Please verify it within 4 minutes.")
                .subject("Clothify OTP Number")
                .build();

        //OTP save in database
        OtpEntity otpEntity = OtpEntity.builder()
                .username(username)
                .otp(otp)
                .expirationTime(LocalDateTime.now().plusMinutes(4))
                .user(user)
                .build();
        otpRepository.save(otpEntity);

        //Send the email
        emailService.sendSimpleMessage(mailBody);
        log.info("email sent successfully");
    }

    @Override
    public AuthenticationResponse verifyOtp(String username, Integer otp) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(username));

        OtpEntity currentOtp = otpRepository.getOtpByUsername(username)
                .orElseThrow(() -> new OtpNotFoundException(username));

        if (!currentOtp.getOtp().equals(otp)) {
            //Throw if otp isn't correct
            throw new OtpIsNotCorrectException(username);
        } else if (currentOtp.getExpirationTime().isBefore(LocalDateTime.now())) {
            //Throw if otp has been expired
            throw new OtpHasBeenExpiredException(username);
        }
        //If otp valid, generate token and send
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }

    private Integer generateOtp() {
        //Generate random number with 6 digits
        return random.nextInt(100_000, 999_999);
    }
}

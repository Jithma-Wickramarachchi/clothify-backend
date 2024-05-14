package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.security.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(User response){
        User user = User.builder()
                .username(response.getUsername())
                .password(passwordEncoder.encode(response.getPassword()))
                .role(response.getRole())
                .build();

        user = repository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
    public AuthenticationResponse authenticate(User request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        User user = repository.findUserByUsername(request.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);

    }
}

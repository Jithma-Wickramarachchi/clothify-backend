package edu.icet.clothifybackend.service.impl.user;

import edu.icet.clothifybackend.controller.user.PasswordDto;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.exception.user.PasswordsDoNotMatchException;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.service.user.ChangePasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordServiceImpl implements ChangePasswordService {
    private final UserRepository repository;
    @Override
    public void changePassword(PasswordDto dto) {
        //Check whether user in the database
        User user = repository.findUserByUsername(dto.getUsername())
                .orElseThrow(()-> new UsernameNotFoundException(dto.getUsername()));

        //Throw if both passwords are not equal
        if (!dto.getPassword().equals(dto.getCheckingPassword())){
            throw new PasswordsDoNotMatchException(dto.getUsername());
        }

        //Change user password and save
        user.setPassword(dto.getPassword());
        repository.save(user);
    }
}

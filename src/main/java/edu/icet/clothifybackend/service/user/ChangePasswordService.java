package edu.icet.clothifybackend.service.user;

import edu.icet.clothifybackend.controller.user.PasswordDto;

public interface ChangePasswordService {
    void changePassword(PasswordDto dto);
}

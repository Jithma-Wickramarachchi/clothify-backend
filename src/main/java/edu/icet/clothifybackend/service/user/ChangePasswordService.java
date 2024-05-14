package edu.icet.clothifybackend.service.user;

import edu.icet.clothifybackend.dto.user.PasswordDto;

public interface ChangePasswordService {
    void changePassword(PasswordDto dto);
}

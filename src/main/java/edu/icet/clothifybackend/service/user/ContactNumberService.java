package edu.icet.clothifybackend.service.user;

import edu.icet.clothifybackend.dto.user.ContactNumberDto;

import java.util.List;

public interface ContactNumberService {
    ContactNumberDto saveContact(ContactNumberDto dto);
    List<ContactNumberDto> getContactListByUsername(String username);
    String deleteContact(String contact);
    ContactNumberDto updateContact(ContactNumberDto dto);
}

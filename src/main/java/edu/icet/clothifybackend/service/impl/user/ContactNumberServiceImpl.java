package edu.icet.clothifybackend.service.impl.user;

import edu.icet.clothifybackend.dto.user.ContactNumberDto;
import edu.icet.clothifybackend.entity.user.AddressEntity;
import edu.icet.clothifybackend.entity.user.ContactNumberEntity;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.exception.user.UserNotFoundException;
import edu.icet.clothifybackend.repository.user.AddressRepository;
import edu.icet.clothifybackend.repository.user.ContactNumberRepository;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.service.user.ContactNumberService;
import edu.icet.clothifybackend.service.util.AddressMapper;
import edu.icet.clothifybackend.service.util.ContactNumberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ContactNumberServiceImpl implements ContactNumberService {
    private final ContactNumberRepository contactRepository;
    private final UserRepository userRepository;
    private final ContactNumberMapper mapper;
    @Override
    public ContactNumberDto saveContact(ContactNumberDto dto) {
        //Throw exception when user not found
        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(dto.getUsername()));

        //convert contact number dto to entity and save
        ContactNumberEntity savedContact = contactRepository.save(mapper.convertDtoToEntity(dto, user));

        //Convert saved entity into dto and return
        return mapper.convertEntityToDto(savedContact);
    }

    @Override
    public List<ContactNumberDto> getContactListByUsername(String username) {
        return null;
    }

    @Override
    public String deleteContact(String contact) {
        return null;
    }

    @Override
    public ContactNumberDto updateContact(ContactNumberDto dto) {
        return null;
    }
}

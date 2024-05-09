package edu.icet.clothifybackend.service.impl.user;

import edu.icet.clothifybackend.dto.user.ContactNumberDto;
import edu.icet.clothifybackend.entity.user.ContactNumberEntity;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.exception.user.ContactNumberNotFoundException;
import edu.icet.clothifybackend.exception.user.UserNotFoundException;
import edu.icet.clothifybackend.repository.user.ContactNumberRepository;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.service.user.ContactNumberService;
import edu.icet.clothifybackend.service.util.ContactNumberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
        List<ContactNumberEntity> entityList = contactRepository.getContactListByUsername(username);
        Iterator<ContactNumberEntity> iterator = entityList.iterator();

        //convert entities into dto and add one by one
        ArrayList<ContactNumberDto> dtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            ContactNumberDto dto = mapper.convertEntityToDto(iterator.next());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public String deleteContact(String contact) {
        //check contact available the database
        if (contactRepository.findById(contact).isEmpty()) {
            throw new ContactNumberNotFoundException(contact);
        }
        contactRepository.deleteById(contact);
        return contact;
    }

    @Override
    public ContactNumberDto updateContact(ContactNumberDto dto) {
        //check whether user in database
        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(dto.getUsername()));

        //check whether contact in database or not
        if (contactRepository.findById(dto.getContactNumber()).isEmpty()) {
            throw new ContactNumberNotFoundException(dto.getContactNumber());
        }
        //convert dto into entity and update
        ContactNumberEntity savedEntity = contactRepository.save(mapper.convertDtoToEntity(dto, user));

        //convert entity into dto and return
        return mapper.convertEntityToDto(savedEntity);    }
}

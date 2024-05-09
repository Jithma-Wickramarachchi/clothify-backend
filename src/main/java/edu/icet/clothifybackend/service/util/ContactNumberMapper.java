package edu.icet.clothifybackend.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.user.ContactNumberDto;
import edu.icet.clothifybackend.entity.user.ContactNumberEntity;
import edu.icet.clothifybackend.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ContactNumberMapper {
    private final ObjectMapper mapper;
    public ContactNumberDto convertEntityToDto(ContactNumberEntity contactEntity){
        ContactNumberDto contactDto = mapper.convertValue(contactEntity, ContactNumberDto.class);
        contactDto.setUsername(contactEntity.getUser().getUsername());
        return contactDto;
    }

    public ContactNumberEntity convertDtoToEntity(ContactNumberDto contactDto, User user){
        ContactNumberEntity contactEntity = mapper.convertValue(contactDto, ContactNumberEntity.class);
        contactEntity.setUser(user);
        return contactEntity;
    }
}

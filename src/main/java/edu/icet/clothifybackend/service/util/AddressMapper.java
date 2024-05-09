package edu.icet.clothifybackend.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.user.AddressDto;
import edu.icet.clothifybackend.entity.user.AddressEntity;
import edu.icet.clothifybackend.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AddressMapper {

    private final ObjectMapper mapper;
    public AddressDto convertEntityToDto(AddressEntity addressEntity){
        AddressDto addressDto = mapper.convertValue(addressEntity, AddressDto.class);
        addressDto.setUsername(addressEntity.getUser().getUsername());
        return addressDto;
    }

    public AddressEntity convertDtoToEntity(AddressDto addressDto, User user){
        AddressEntity addressEntity = mapper.convertValue(addressDto, AddressEntity.class);
        addressEntity.setUser(user);
        return addressEntity;
    }
}

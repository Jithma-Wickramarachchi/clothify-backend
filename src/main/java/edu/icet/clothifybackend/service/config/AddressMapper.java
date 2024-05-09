package edu.icet.clothifybackend.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.AddressDto;
import edu.icet.clothifybackend.entity.AddressEntity;
import edu.icet.clothifybackend.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AddressMapper {

    private final ObjectMapper mapper;
    public AddressDto convertEntityToDto(AddressEntity addressEntity){
        AddressDto addressDto = mapper.convertValue(addressEntity, AddressDto.class);
        addressDto.setUserId(addressEntity.getUser().getId());
        return addressDto;
    }

    public AddressEntity convertDtoToEntity(AddressDto addressDto, User user){
        AddressEntity addressEntity = mapper.convertValue(addressDto, AddressEntity.class);
        addressEntity.setUser(user);
        return addressEntity;
    }
}

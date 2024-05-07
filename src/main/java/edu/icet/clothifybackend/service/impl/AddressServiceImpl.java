package edu.icet.clothifybackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.AddressDto;
import edu.icet.clothifybackend.entity.AddressEntity;
import edu.icet.clothifybackend.entity.UserEntity;
import edu.icet.clothifybackend.exception.UserNotFoundByUserId;
import edu.icet.clothifybackend.repository.AddressRepository;
import edu.icet.clothifybackend.repository.UserRepository;
import edu.icet.clothifybackend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final ObjectMapper mapper;
    @Override
    public AddressDto saveAddress(AddressDto dto) {
        //Throw exception when user not found
        UserEntity userEntity = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new UserNotFoundByUserId(dto.getUserId()));

        //convert address dto to entity
        AddressEntity addressEntity = mapper.convertValue(dto, AddressEntity.class);
        addressEntity.setUser(userEntity);

        //Convert saved entity into dto and return
        return mapper.convertValue(
                addressRepository.save(addressEntity),
                AddressDto.class
        );
    }

    @Override
    public List<AddressDto> getListByUserId(Long id) {
        return null;
    }

    @Override
    public Long deleteAddress(Long id) {
        return null;
    }

    @Override
    public AddressDto updateAddress(AddressDto dto) {
        return null;
    }
}

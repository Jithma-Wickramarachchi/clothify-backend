package edu.icet.clothifybackend.service.impl;

import edu.icet.clothifybackend.dto.AddressDto;
import edu.icet.clothifybackend.entity.AddressEntity;
import edu.icet.clothifybackend.entity.UserEntity;
import edu.icet.clothifybackend.exception.AddressNotFoundException;
import edu.icet.clothifybackend.exception.UserNotFoundException;
import edu.icet.clothifybackend.repository.AddressRepository;
import edu.icet.clothifybackend.repository.UserRepository;
import edu.icet.clothifybackend.service.AddressService;
import edu.icet.clothifybackend.service.config.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final AddressMapper mapper;
    @Override
    public AddressDto saveAddress(AddressDto dto) {
        //Throw exception when user not found
        UserEntity userEntity = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new UserNotFoundException(dto.getUserId()));

        //convert address dto to entity and save
        AddressEntity savedAddress = addressRepository.save(mapper.convertDtoToEntity(dto, userEntity));

        //Convert saved entity into dto and return
        return mapper.convertEntityToDto(savedAddress);
    }

    @Override
    public List<AddressDto> getAddressListByUserId(Long id) {
        List<AddressEntity> entityList = addressRepository.getAddressListByUserId(id);
        Iterator<AddressEntity> iterator = entityList.iterator();

        //convert entities into dto and add one by one
        ArrayList<AddressDto> dtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            AddressDto dto = mapper.convertEntityToDto(iterator.next());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Long deleteAddress(Long id) {
        //check id available the database
        if (addressRepository.findById(id).isEmpty()) {
            throw new AddressNotFoundException(id);
        }
        addressRepository.deleteById(id);
        return id;
    }

    @Override
    public AddressDto updateAddress(AddressDto dto) {
        //check whether user in database
        UserEntity userEntity = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new UserNotFoundException(dto.getUserId()));

        //check whether address in database
        addressRepository.findById(dto.getId())
                .orElseThrow(()-> new AddressNotFoundException(dto.getId()));

        //convert dto into entity and update
        AddressEntity savedEntity = addressRepository.save(mapper.convertDtoToEntity(dto, userEntity));

        //convert entity into dto and return
        return mapper.convertEntityToDto(savedEntity);
    }
}

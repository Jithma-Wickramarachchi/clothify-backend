package edu.icet.clothifybackend.service.impl.user;

import edu.icet.clothifybackend.dto.user.AddressDto;
import edu.icet.clothifybackend.entity.user.AddressEntity;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.exception.user.AddressNotFoundException;
import edu.icet.clothifybackend.exception.user.UserNotFoundException;
import edu.icet.clothifybackend.repository.user.AddressRepository;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.service.user.AddressService;
import edu.icet.clothifybackend.service.util.AddressMapper;
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
        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(dto.getUsername()));

        //convert address dto to entity and save
        AddressEntity savedAddress = addressRepository.save(mapper.convertDtoToEntity(dto, user));

        //Convert saved entity into dto and return
        return mapper.convertEntityToDto(savedAddress);
    }
    @Override
    public List<AddressDto> getAddressListByUsername(String username) {
        List<AddressEntity> entityList = addressRepository.getAddressListByUsername(username);
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
        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(dto.getUsername()));

        //check whether address in database
        if (addressRepository.findById(dto.getId()).isEmpty()) {
                throw new AddressNotFoundException(dto.getId());
        }
        //convert dto into entity and update
        AddressEntity savedEntity = addressRepository.save(mapper.convertDtoToEntity(dto, user));

        //convert entity into dto and return
        return mapper.convertEntityToDto(savedEntity);
    }
}

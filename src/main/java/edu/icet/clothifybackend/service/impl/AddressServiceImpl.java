package edu.icet.clothifybackend.service.impl;

import edu.icet.clothifybackend.dto.AddressDto;
import edu.icet.clothifybackend.repository.AddressRepository;
import edu.icet.clothifybackend.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressRepository repository;
    @Override
    public AddressDto saveAddress(AddressDto dto) {
        return null;
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

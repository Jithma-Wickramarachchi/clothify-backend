package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.dto.AddressDto;

import java.util.List;

public interface AddressService {
    AddressDto saveAddress(AddressDto dto);
    List<AddressDto> getAddressListByUserId(Long id);
    Long deleteAddress(Long id);
    AddressDto updateAddress(AddressDto dto);
}

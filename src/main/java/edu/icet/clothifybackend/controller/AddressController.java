package edu.icet.clothifybackend.controller;

import edu.icet.clothifybackend.dto.AddressDto;
import edu.icet.clothifybackend.dto.ItemDto;
import edu.icet.clothifybackend.service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/address")
public class AddressController {
    private final AddressService service;
    @PostMapping
    public ResponseEntity<AddressDto> saveAddress(@Valid @RequestBody AddressDto dto){
        return new ResponseEntity<>(service.saveAddress(dto), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<AddressDto>> getAddressListByUserId(Long id){
        return new ResponseEntity<>(service.getAddressListByUserId(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id){
        Long deletedAddressId = service.deleteAddress(id);
        return new ResponseEntity<>("Address("+deletedAddressId+") has been deleted successfully!", HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<AddressDto> updateAddress(@Valid @RequestBody AddressDto dto){
        return new ResponseEntity<>(service.updateAddress(dto), HttpStatus.OK);
    }
}

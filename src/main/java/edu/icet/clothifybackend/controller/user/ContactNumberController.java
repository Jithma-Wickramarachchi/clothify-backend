package edu.icet.clothifybackend.controller.user;

import edu.icet.clothifybackend.dto.user.ContactNumberDto;
import edu.icet.clothifybackend.service.user.ContactNumberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user/contact")
public class ContactNumberController {
    private final ContactNumberService service;
    @PostMapping
    public ResponseEntity<ContactNumberDto> saveContact(@Valid @RequestBody ContactNumberDto dto){
        return new ResponseEntity<>(service.saveContact(dto), HttpStatus.CREATED);
    }
    @GetMapping("/{username}")
    public ResponseEntity<List<ContactNumberDto>> getContactListByUsername(@PathVariable String username){
        return new ResponseEntity<>(service.getContactListByUsername(username), HttpStatus.OK);
    }
    @DeleteMapping("/{contact}")
    public ResponseEntity<String> deleteAddress(@PathVariable String contact){
        String deletedContact = service.deleteContact(contact);
        return new ResponseEntity<>("Contact Number("+deletedContact+") has been deleted successfully!", HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity<ContactNumberDto> updateContact(@Valid @RequestBody ContactNumberDto dto){
        return new ResponseEntity<>(service.updateContact(dto), HttpStatus.OK);
    }
}

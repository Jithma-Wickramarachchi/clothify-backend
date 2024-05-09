package edu.icet.clothifybackend.service.impl.user;

import edu.icet.clothifybackend.dto.user.PaymentDetailsDto;
import edu.icet.clothifybackend.entity.user.PaymentDetailsEntity;
import edu.icet.clothifybackend.entity.user.User;
import edu.icet.clothifybackend.exception.user.PaymentDetailsNotFoundException;
import edu.icet.clothifybackend.exception.user.UserNotFoundException;
import edu.icet.clothifybackend.repository.user.PaymentDetailsRepository;
import edu.icet.clothifybackend.repository.user.UserRepository;
import edu.icet.clothifybackend.service.user.PaymentDetailsService;
import edu.icet.clothifybackend.service.util.PaymentDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
@RequiredArgsConstructor
public class PaymentDetailsServiceImpl implements PaymentDetailsService {

    private final PaymentDetailsRepository paymentRepository;
    private final UserRepository userRepository;
    private final PaymentDetailsMapper mapper;
    @Override
    public PaymentDetailsDto savePaymentDetails(PaymentDetailsDto dto) {
        //Throw exception when user not found
        User user = userRepository.findUserByUsername(dto.getUsername())
                .orElseThrow(()-> new UserNotFoundException(dto.getUsername()));

        //convert paymentDetailsDto to entity and save
        PaymentDetailsEntity savedPaymentEntity = paymentRepository.save(mapper.convertDtoToEntity(dto, user));

        //Convert saved entity into dto and return
        return mapper.convertEntityToDto(savedPaymentEntity);
    }

    @Override
    public List<PaymentDetailsDto> getPaymentDetailsListByUsername(String username) {
        List<PaymentDetailsEntity> entityList = paymentRepository.getPaymentDetailsListByUsername(username);
        Iterator<PaymentDetailsEntity> iterator = entityList.iterator();

        //convert entities into dto and add one by one
        ArrayList<PaymentDetailsDto> dtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            PaymentDetailsDto dto = mapper.convertEntityToDto(iterator.next());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Long deletePaymentDetails(Long id) {
        //check id available the database
        if (paymentRepository.findById(id).isEmpty()) {
            throw new PaymentDetailsNotFoundException(id);
        }
        //delete id
        paymentRepository.deleteById(id);
        return id;
    }

    @Override
    public PaymentDetailsDto updatePaymentDetails(PaymentDetailsDto dto){
    //check whether user in database
    User user = userRepository.findUserByUsername(dto.getUsername())
            .orElseThrow(()-> new UserNotFoundException(dto.getUsername()));

    //check whether payment details in database
        if (paymentRepository.findById(dto.getId()).isEmpty()) {
        throw new PaymentDetailsNotFoundException(dto.getId());
    }
    //convert dto into entity and update
    PaymentDetailsEntity savedEntity = paymentRepository.save(mapper.convertDtoToEntity(dto, user));

    //convert entity into dto and return
        return mapper.convertEntityToDto(savedEntity);
    }
}

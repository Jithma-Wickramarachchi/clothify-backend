package edu.icet.clothifybackend.exception;

import edu.icet.clothifybackend.exception.item.ItemIdNotFoundException;
import edu.icet.clothifybackend.exception.item.ItemImageNotFoundException;
import edu.icet.clothifybackend.exception.user.AddressNotFoundException;
import edu.icet.clothifybackend.exception.user.ContactNumberNotFoundException;
import edu.icet.clothifybackend.exception.user.PaymentDetailsNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(StockIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStockIdNotFoundException(StockIdNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(ItemIdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemIdNotFoundException(ItemIdNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(ItemImageNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleItemImageNotFoundException(ItemImageNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(AddressNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFoundException(AddressNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(PaymentDetailsNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePaymentDetailsNotFoundException(PaymentDetailsNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(ContactNumberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleContactNumberNotFoundException(ContactNumberNotFoundException ex){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentsNotValidException(MethodArgumentNotValidException ex){
        return getMapResponseEntity(ex);
    }
    private ResponseEntity<Map<String, String>> getMapResponseEntity(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
        });
        log.info(String.valueOf(errors));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}

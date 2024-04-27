package edu.icet.clothifybackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.StockDto;
import edu.icet.clothifybackend.entity.StockEntity;
import edu.icet.clothifybackend.exception.StockIdNotFoundException;
import edu.icet.clothifybackend.repository.StockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTests {
    @Mock
    private StockRepository repository;
    @Mock
    private ObjectMapper mapper;
    @InjectMocks
    private StockServiceImpl service;

    private StockDto stockDto;
    private StockDto stockDtoWithId;
    private StockEntity stockEntity;
    @BeforeEach
    public void init(){
        this.stockDto = StockDto.builder()
                .stockId(null)
                .companyName("Brandix")
                .initialItemCount(514)
                .availableItemCount(210)
                .date(LocalDate.parse("2024-04-23"))
                .build();

        this.stockEntity = StockEntity.builder()
                .stockId(1L)
                .companyName("Brandix")
                .initialItemCount(514)
                .availableItemCount(210)
                .date(LocalDate.parse("2024-04-23"))
                .build();

        this.stockDtoWithId = StockDto.builder()
                .stockId(1L)
                .companyName("Brandix")
                .initialItemCount(514)
                .availableItemCount(210)
                .date(LocalDate.parse("2024-04-23"))
                .build();
    }
    @Test
    @DisplayName("Stock successfully creation")
    void GivenStockDto_WhenSaveStock_thenReturnSavedDto(){

        when(repository.save(Mockito.any(StockEntity.class))).thenReturn(stockEntity);
        when(mapper.convertValue(stockDto, StockEntity.class)).thenReturn(stockEntity);
        when(mapper.convertValue(stockEntity, StockDto.class)).thenReturn(stockDtoWithId);

        StockDto savedDto = service.saveStock(stockDto);

        Assertions.assertThat(savedDto).isNotNull();
        Assertions.assertThat(savedDto.getStockId()).isEqualTo(stockDtoWithId.getStockId());
        Assertions.assertThat(savedDto.getCompanyName()).isEqualTo(stockDtoWithId.getCompanyName());
        Assertions.assertThat(savedDto.getInitialItemCount()).isEqualTo(stockDtoWithId.getInitialItemCount());
        Assertions.assertThat(savedDto.getAvailableItemCount()).isEqualTo(stockDtoWithId.getAvailableItemCount());
        Assertions.assertThat(savedDto.getDate()).isEqualTo(stockDtoWithId.getDate());
    }

    @Test
    @DisplayName("Get stock by stockId success")
    void GivenStockDtoId_WhenStockIdFound_thenReturnStockDto(){

        when(repository.getStockByStockId(stockDto.getStockId())).thenReturn(Optional.ofNullable(stockEntity));
        when(mapper.convertValue(stockEntity, StockDto.class)).thenReturn(stockDtoWithId);

        StockDto savedDto = service.getStockByStockId(stockDto.getStockId());

        Assertions.assertThat(savedDto).isNotNull();
        Assertions.assertThat(savedDto.getStockId()).isEqualTo(stockDtoWithId.getStockId());
        Assertions.assertThat(savedDto.getCompanyName()).isEqualTo(stockDtoWithId.getCompanyName());
        Assertions.assertThat(savedDto.getInitialItemCount()).isEqualTo(stockDtoWithId.getInitialItemCount());
        Assertions.assertThat(savedDto.getAvailableItemCount()).isEqualTo(stockDtoWithId.getAvailableItemCount());
        Assertions.assertThat(savedDto.getDate()).isEqualTo(stockDtoWithId.getDate());
    }
    @Test()
    @DisplayName("Get stock by stockId failure")
    void GivenStockDtoId_WhenStockIdNotFound_thenThrowStockIdNotFoundException(){
        Long stockId = stockDtoWithId.getStockId();
        when(repository.getStockByStockId(stockId)).thenThrow(new StockIdNotFoundException(stockDtoWithId.getStockId()));

        StockIdNotFoundException stockIdNotFoundException = assertThrows(StockIdNotFoundException.class,
                () -> service.getStockByStockId(stockId));

        assertEquals("Stock Id not found! Id:"+stockId, stockIdNotFoundException.getMessage());
    }
}

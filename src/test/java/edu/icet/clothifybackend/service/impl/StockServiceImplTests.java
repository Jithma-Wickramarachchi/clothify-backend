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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
    void givenStockDtoId_whenGetStockIdFound_thenReturnStockDto(){

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
    @Test
    @DisplayName("Get stock by stockId failure")
    void givenStockDtoId_whenGetStockIdNotFound_thenThrowStockIdNotFoundException(){
        Long stockId = stockDtoWithId.getStockId();
        when(repository.getStockByStockId(stockId)).thenThrow(new StockIdNotFoundException(stockDtoWithId.getStockId()));

        StockIdNotFoundException stockIdNotFoundException = assertThrows(StockIdNotFoundException.class,
                () -> service.getStockByStockId(stockId));

        assertEquals("Stock Id not found! Id:"+stockId, stockIdNotFoundException.getMessage());
    }
    @Test
    @DisplayName("Get All stocks success")
    void whenCallMethod_thenReturnAllStocksAsList(){
        List<StockEntity> stockList = Arrays.asList(stockEntity, stockEntity, stockEntity);
        when(repository.getAllStocks()).thenReturn(stockList);
        when(mapper.convertValue(stockEntity,StockDto.class)).thenReturn(stockDtoWithId);

        List<StockDto> savedDtoList = service.getAllStocks();

        assertThat(savedDtoList).hasSize(stockList.size());

        //check first dto in savedDtoList
        assertThat(savedDtoList.get(0).getStockId()).isEqualTo(stockEntity.getStockId());
        assertThat(savedDtoList.get(0).getCompanyName()).isEqualTo(stockEntity.getCompanyName());
        assertThat(savedDtoList.get(0).getInitialItemCount()).isEqualTo(stockEntity.getInitialItemCount());
        assertThat(savedDtoList.get(0).getAvailableItemCount()).isEqualTo(stockEntity.getAvailableItemCount());
        assertThat(savedDtoList.get(0).getDate()).isEqualTo(stockEntity.getDate());

        //check second dto in savedDtoList
        assertThat(savedDtoList.get(1).getStockId()).isEqualTo(stockEntity.getStockId());
        assertThat(savedDtoList.get(1).getCompanyName()).isEqualTo(stockEntity.getCompanyName());
        assertThat(savedDtoList.get(1).getInitialItemCount()).isEqualTo(stockEntity.getInitialItemCount());
        assertThat(savedDtoList.get(1).getAvailableItemCount()).isEqualTo(stockEntity.getAvailableItemCount());
        assertThat(savedDtoList.get(1).getDate()).isEqualTo(stockEntity.getDate());

        //check first dto in savedDtoList
        assertThat(savedDtoList.get(2).getStockId()).isEqualTo(stockEntity.getStockId());
        assertThat(savedDtoList.get(2).getCompanyName()).isEqualTo(stockEntity.getCompanyName());
        assertThat(savedDtoList.get(2).getInitialItemCount()).isEqualTo(stockEntity.getInitialItemCount());
        assertThat(savedDtoList.get(2).getAvailableItemCount()).isEqualTo(stockEntity.getAvailableItemCount());
        assertThat(savedDtoList.get(2).getDate()).isEqualTo(stockEntity.getDate());
    }

    @Test
    @DisplayName("Delete stock by stockId success")
    void givenStockDtoId_whenDeleteStockIdFound_thenDeleteAndReturnStockDtoId(){

        when(repository.getStockByStockId(stockDtoWithId.getStockId())).thenReturn(Optional.ofNullable(stockEntity));

        Long deletedStockId = service.deleteStockById(stockDtoWithId.getStockId());

        assertThat(deletedStockId).isEqualTo(stockDtoWithId.getStockId());
    }
    @Test
    @DisplayName("Delete stock by stockId failure")
    void givenStockDtoId_whenDeleteStockIdNotFound_thenThrowStockIdNotFoundException(){

        Long stockId = stockDtoWithId.getStockId();
        when(repository.getStockByStockId(stockId)).thenReturn(Optional.empty());

        StockIdNotFoundException stockIdNotFoundException = assertThrows(StockIdNotFoundException.class,
                () -> service.deleteStockById(stockId));

        assertEquals("Stock Id not found! Id:"+stockId, stockIdNotFoundException.getMessage());
    }

    @Test
    @DisplayName("Update stock by stockId success")
    void givenStockDtoId_whenUpdateStockDtoFound_thenUpdateAndReturnStockDto(){

        when(repository.getStockByStockId(stockDtoWithId.getStockId())).thenReturn(Optional.ofNullable(stockEntity));
        when(repository.save(stockEntity)).thenReturn(stockEntity);
        when(mapper.convertValue(stockDtoWithId, StockEntity.class)).thenReturn(stockEntity);
        when(mapper.convertValue(stockEntity, StockDto.class)).thenReturn(stockDtoWithId);

        StockDto updatedStockDto = service.updateStock(stockDtoWithId);

        assertThat(updatedStockDto)
                .isEqualTo(stockDtoWithId);

        assertThat(updatedStockDto.getStockId()).isEqualTo(stockDtoWithId.getStockId());
        assertThat(updatedStockDto.getCompanyName()).isEqualTo(stockDtoWithId.getCompanyName());
        assertThat(updatedStockDto.getInitialItemCount()).isEqualTo(stockDtoWithId.getInitialItemCount());
        assertThat(updatedStockDto.getAvailableItemCount()).isEqualTo(stockDtoWithId.getAvailableItemCount());
        assertThat(updatedStockDto.getDate()).isEqualTo(stockDtoWithId.getDate());

    }
    @Test
    @DisplayName("Update stock by stockId failure")
    void givenStockDtoId_whenUpdateStockDtoNotFound_thenThrowsStockIdNotFoundException(){

        Long stockId = stockDtoWithId.getStockId();
        when(repository.getStockByStockId(stockId)).thenReturn(Optional.empty());

        StockIdNotFoundException stockIdNotFoundException = assertThrows(StockIdNotFoundException.class,
                () -> service.updateStock(stockDtoWithId));

        assertEquals("Stock Id not found! Id:"+stockId, stockIdNotFoundException.getMessage());
    }
}

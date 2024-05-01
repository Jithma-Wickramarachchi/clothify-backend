package edu.icet.clothifybackend.service.impl;

import edu.icet.clothifybackend.dto.ItemDto;
import edu.icet.clothifybackend.entity.ItemEntity;
import edu.icet.clothifybackend.entity.StockEntity;
import edu.icet.clothifybackend.exception.ItemIdNotFoundException;
import edu.icet.clothifybackend.exception.StockIdNotFoundException;
import edu.icet.clothifybackend.repository.ItemRepository;
import edu.icet.clothifybackend.repository.StockRepository;
import edu.icet.clothifybackend.service.config.ItemMapper;
import edu.icet.clothifybackend.util.Category;
import edu.icet.clothifybackend.util.Size;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class ItemServiceImplTests {
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private StockRepository stockRepository;
    @Mock
    private ItemMapper mapper;
    @InjectMocks
    private ItemServiceImpl service;

    private ItemDto itemDto;
    private ItemDto itemDtoWithId;
    private ItemEntity itemEntity;
    private StockEntity stockEntity;

    @BeforeEach
    public void init() {
        this.itemDto = ItemDto.builder()
                .itemId(null)
                .name("Tommy Hilfiger - red")
                .category(Category.MEN)
                .count(36)
                .description("")
                .price(3020D)
                .size(Size.M)
                .stockId(1L)
                .build();

        this.stockEntity = StockEntity.builder()
                .stockId(1L)
                .companyName("Brandix")
                .initialItemCount(514)
                .availableItemCount(210)
                .date(LocalDate.parse("2024-04-23"))
                .itemList(new ArrayList<>())
                .build();

        this.itemEntity = ItemEntity.builder()
                .itemId(1L)
                .name("Tommy Hilfiger - red")
                .category(Category.MEN)
                .count(36)
                .description("")
                .price(3020D)
                .size(Size.M)
                .stock(stockEntity)
                .build();

        this.itemDtoWithId = ItemDto.builder()
                .itemId(1L)
                .name("Tommy Hilfiger - red")
                .category(Category.MEN)
                .count(36)
                .description("")
                .price(3020D)
                .size(Size.M)
                .stockId(1L)
                .build();
    }

    @Test
    @DisplayName("Item successfully creation")
    void GivenItemDto_WhenSaveItem_thenReturnSavedDto() {

        when(stockRepository.save(Mockito.any(StockEntity.class))).thenReturn(stockEntity);
        when(stockRepository.getStockByStockId(itemDto.getStockId())).thenReturn(Optional.ofNullable(stockEntity));
        when(mapper.convertDtoToEntity(itemDto, stockEntity)).thenReturn(itemEntity);
        when(mapper.convertEntityToDto(itemEntity)).thenReturn(itemDtoWithId);

        ItemDto savedDto = service.createItem(itemDto);

        assertThat(savedDto).isNotNull();
        assertThat(savedDto.getItemId()).isEqualTo(itemDtoWithId.getItemId());
        assertThat(savedDto.getName()).isEqualTo(itemDtoWithId.getName());
        assertThat(savedDto.getDescription()).isEqualTo(itemDtoWithId.getDescription());
        assertThat(savedDto.getPrice()).isEqualTo(itemDtoWithId.getPrice());
        assertThat(savedDto.getCategory()).isEqualTo(itemDtoWithId.getCategory());
        assertThat(savedDto.getSize()).isEqualTo(itemDtoWithId.getSize());
        assertThat(savedDto.getCount()).isEqualTo(itemDtoWithId.getCount());
        assertThat(savedDto.getStockId()).isEqualTo(itemDtoWithId.getStockId());
    }
    @Test
    @DisplayName("Item Creation Failure")
    void givenItemDto_whenStockIdNotFound_thenThrowStockIdNotFoundException(){
        Long stockId = itemDto.getStockId();
        when(stockRepository.getStockByStockId(stockId)).thenThrow(new StockIdNotFoundException(stockId));

        StockIdNotFoundException stockIdNotFoundException = assertThrows(StockIdNotFoundException.class,
                () -> service.createItem(itemDto));

        assertEquals("Stock Id not found! Id:"+stockId, stockIdNotFoundException.getMessage());
    }
    @Test
    @DisplayName("Get item by itemId success")
    void givenItemDtoId_whenGetItemIdFound_thenReturnItemDto(){

        when(itemRepository.getItemByItemId((itemDtoWithId.getItemId()))).thenReturn(Optional.ofNullable(itemEntity));
        when(mapper.convertEntityToDto(any(ItemEntity.class))).thenReturn(itemDtoWithId);

        ItemDto savedDto = service.getItemByItemId(itemDtoWithId.getItemId());

        assertThat(savedDto).isNotNull();
        assertThat(savedDto.getItemId()).isEqualTo(itemDtoWithId.getItemId());
        assertThat(savedDto.getName()).isEqualTo(itemDtoWithId.getName());
        assertThat(savedDto.getDescription()).isEqualTo(itemDtoWithId.getDescription());
        assertThat(savedDto.getPrice()).isEqualTo(itemDtoWithId.getPrice());
        assertThat(savedDto.getCategory()).isEqualTo(itemDtoWithId.getCategory());
        assertThat(savedDto.getSize()).isEqualTo(itemDtoWithId.getSize());
        assertThat(savedDto.getCount()).isEqualTo(itemDtoWithId.getCount());
        assertThat(savedDto.getStockId()).isEqualTo(itemDtoWithId.getStockId());
    }
    @Test
    @DisplayName("Get stock by stockId failure")
    void givenItemDtoId_whenGetItemIdNotFound_thenThrowItemIdNotFoundException(){
        Long itemId = itemDtoWithId.getItemId();
        when(itemRepository.getItemByItemId(itemId)).thenThrow(new ItemIdNotFoundException(itemId));

        ItemIdNotFoundException itemIdNotFoundException = assertThrows(ItemIdNotFoundException.class,
                () -> service.getItemByItemId(itemId));

        assertEquals("Item Id not found! id:"+itemId, itemIdNotFoundException.getMessage());
    }
    @Test
    @DisplayName("Get All items success")
    void whenCallMethod_thenReturnAllItemsAsList(){
        List<ItemEntity> itemList = Arrays.asList(itemEntity, itemEntity, itemEntity);
        when(itemRepository.getAllItems()).thenReturn(itemList);
        when(mapper.convertEntityToDto(any(ItemEntity.class))).thenReturn(itemDtoWithId);

        List<ItemDto> savedDtoList = service.getAllItems();

        assertThat(savedDtoList).hasSize(itemList.size());

        //check first dto in savedDtoList
        assertThat(savedDtoList.get(0).getItemId()).isEqualTo(itemDtoWithId.getItemId());
        assertThat(savedDtoList.get(0).getName()).isEqualTo(itemDtoWithId.getName());
        assertThat(savedDtoList.get(0).getDescription()).isEqualTo(itemDtoWithId.getDescription());
        assertThat(savedDtoList.get(0).getPrice()).isEqualTo(itemDtoWithId.getPrice());
        assertThat(savedDtoList.get(0).getCategory()).isEqualTo(itemDtoWithId.getCategory());
        assertThat(savedDtoList.get(0).getSize()).isEqualTo(itemDtoWithId.getSize());
        assertThat(savedDtoList.get(0).getCount()).isEqualTo(itemDtoWithId.getCount());
        assertThat(savedDtoList.get(0).getStockId()).isEqualTo(itemDtoWithId.getStockId());

        //check second dto in savedDtoList
        assertThat(savedDtoList.get(1).getItemId()).isEqualTo(itemDtoWithId.getItemId());
        assertThat(savedDtoList.get(1).getName()).isEqualTo(itemDtoWithId.getName());
        assertThat(savedDtoList.get(1).getDescription()).isEqualTo(itemDtoWithId.getDescription());
        assertThat(savedDtoList.get(1).getPrice()).isEqualTo(itemDtoWithId.getPrice());
        assertThat(savedDtoList.get(1).getCategory()).isEqualTo(itemDtoWithId.getCategory());
        assertThat(savedDtoList.get(1).getSize()).isEqualTo(itemDtoWithId.getSize());
        assertThat(savedDtoList.get(1).getCount()).isEqualTo(itemDtoWithId.getCount());
        assertThat(savedDtoList.get(1).getStockId()).isEqualTo(itemDtoWithId.getStockId());

        //check first dto in savedDtoList
        assertThat(savedDtoList.get(2).getItemId()).isEqualTo(itemDtoWithId.getItemId());
        assertThat(savedDtoList.get(2).getName()).isEqualTo(itemDtoWithId.getName());
        assertThat(savedDtoList.get(2).getDescription()).isEqualTo(itemDtoWithId.getDescription());
        assertThat(savedDtoList.get(2).getPrice()).isEqualTo(itemDtoWithId.getPrice());
        assertThat(savedDtoList.get(2).getCategory()).isEqualTo(itemDtoWithId.getCategory());
        assertThat(savedDtoList.get(2).getSize()).isEqualTo(itemDtoWithId.getSize());
        assertThat(savedDtoList.get(2).getCount()).isEqualTo(itemDtoWithId.getCount());
        assertThat(savedDtoList.get(2).getStockId()).isEqualTo(itemDtoWithId.getStockId());
    }

    @Test
    @DisplayName("Delete stock by stockId success")
    void givenItemDtoId_whenDeleteItemIdFound_thenDeleteAndReturnItemDtoId(){

        when(itemRepository.getItemByItemId(itemDtoWithId.getItemId())).thenReturn(Optional.ofNullable(itemEntity));

        Long deletedItemId = service.deleteItemById(itemDtoWithId.getItemId());

        assertThat(deletedItemId).isEqualTo(itemDtoWithId.getItemId());
    }
    @Test
    @DisplayName("Delete item by itemId failure")
    void givenItemDtoId_whenDeleteItemIdNotFound_thenThrowItemIdNotFoundException(){

        Long itemId = itemDtoWithId.getStockId();
        when(itemRepository.getItemByItemId(itemId)).thenReturn(Optional.empty());

        ItemIdNotFoundException itemIdNotFoundException = assertThrows(ItemIdNotFoundException.class,
                () -> service.deleteItemById(itemId));

        assertEquals("Item Id not found! id:"+itemId, itemIdNotFoundException.getMessage());
    }
    @Test
    @DisplayName("Update item by itemId success")
    void givenStockDtoId_whenUpdateStockDtoFound_thenUpdateAndReturnStockDto(){

        when(stockRepository.getStockByStockId(itemDtoWithId.getStockId())).thenReturn(Optional.ofNullable(stockEntity));
        when(itemRepository.getItemByItemId(itemDtoWithId.getItemId())).thenReturn(Optional.ofNullable(itemEntity));
        when(itemRepository.save(itemEntity)).thenReturn(itemEntity);
        when(mapper.convertDtoToEntity(itemDtoWithId, stockEntity)).thenReturn(itemEntity);
        when(mapper.convertEntityToDto(itemEntity)).thenReturn(itemDtoWithId);

        ItemDto updatedItemDto = service.updateItem(itemDtoWithId);

        assertThat(updatedItemDto).isEqualTo(itemDtoWithId);
        assertThat(updatedItemDto.getItemId()).isEqualTo(itemDtoWithId.getItemId());
        assertThat(updatedItemDto.getName()).isEqualTo(itemDtoWithId.getName());
        assertThat(updatedItemDto.getDescription()).isEqualTo(itemDtoWithId.getDescription());
        assertThat(updatedItemDto.getPrice()).isEqualTo(itemDtoWithId.getPrice());
        assertThat(updatedItemDto.getCategory()).isEqualTo(itemDtoWithId.getCategory());
        assertThat(updatedItemDto.getSize()).isEqualTo(itemDtoWithId.getSize());
        assertThat(updatedItemDto.getCount()).isEqualTo(itemDtoWithId.getCount());
        assertThat(updatedItemDto.getStockId()).isEqualTo(itemDtoWithId.getStockId());
    }
    @Test
    @DisplayName("Update item by itemId failure on wrong stockId")
    void givenItemDtoId_whenUpdateItemDtoStockNotFound_thenThrowsStockIdNotFoundException(){

        Long stockId = itemDtoWithId.getStockId();
        when(stockRepository.getStockByStockId(stockId)).thenReturn(Optional.empty());

        StockIdNotFoundException stockIdNotFoundException = assertThrows(StockIdNotFoundException.class,
                () -> service.updateItem(itemDtoWithId));

        assertEquals("Stock Id not found! Id:"+stockId, stockIdNotFoundException.getMessage());
    }
    @Test
    @DisplayName("Update item by itemId failure on wrong itemId")
    void givenItemDtoId_whenUpdateItemDtoNotFound_thenThrowsItemIdNotFoundException(){

        Long itemId = itemDtoWithId.getItemId();
        Long stockId = itemDtoWithId.getStockId();
        when(stockRepository.getStockByStockId(stockId)).thenReturn(Optional.ofNullable(stockEntity));
        when(itemRepository.getItemByItemId(itemId)).thenReturn(Optional.empty());

        ItemIdNotFoundException itemIdNotFoundException = assertThrows(ItemIdNotFoundException.class,
                () -> service.updateItem(itemDtoWithId));

        assertEquals("Item Id not found! id:"+itemId, itemIdNotFoundException.getMessage());
    }
}

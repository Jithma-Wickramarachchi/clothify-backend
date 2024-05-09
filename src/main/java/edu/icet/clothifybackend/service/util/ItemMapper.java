package edu.icet.clothifybackend.service.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.item.ItemDto;
import edu.icet.clothifybackend.entity.item.ItemEntity;
import edu.icet.clothifybackend.entity.StockEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ItemMapper {
    private final ObjectMapper mapper;
    public ItemDto convertEntityToDto(ItemEntity itemEntity){
        ItemDto itemDto = mapper.convertValue(itemEntity, ItemDto.class);
        itemDto.setStockId(itemEntity.getStock().getStockId());
        return itemDto;
    }

    public ItemEntity convertDtoToEntity(ItemDto itemDto, StockEntity stockEntity){
        ItemEntity itemEntity = mapper.convertValue(itemDto, ItemEntity.class);
        itemEntity.setStock(stockEntity);
        return itemEntity;
    }
}

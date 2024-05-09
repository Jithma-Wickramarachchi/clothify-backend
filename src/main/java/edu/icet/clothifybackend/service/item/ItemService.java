package edu.icet.clothifybackend.service.item;

import edu.icet.clothifybackend.dto.item.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto dto);
    ItemDto getItemByItemId(Long itemId);
    List<ItemDto> getAllItems();
    Long deleteItemById(Long itemId);
    ItemDto updateItem(ItemDto dto);
}

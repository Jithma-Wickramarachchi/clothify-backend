package edu.icet.clothifybackend.service;

import edu.icet.clothifybackend.dto.ItemDto;

import java.util.List;

public interface ItemService {
    ItemDto createItem(ItemDto dto);
    ItemDto getItemByItemId(Long itemId);
    List<ItemDto> getAllItems();
    void deleteItemById(Long itemId);
    ItemDto updateItem(ItemDto dto);
}

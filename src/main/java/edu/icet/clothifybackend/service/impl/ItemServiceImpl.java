package edu.icet.clothifybackend.service.impl;

import edu.icet.clothifybackend.dto.ItemDto;
import edu.icet.clothifybackend.entity.ItemEntity;
import edu.icet.clothifybackend.entity.StockEntity;
import edu.icet.clothifybackend.exception.ItemIdNotFoundException;
import edu.icet.clothifybackend.exception.StockIdNotFoundException;
import edu.icet.clothifybackend.repository.ItemRepository;
import edu.icet.clothifybackend.repository.StockRepository;
import edu.icet.clothifybackend.service.ItemService;
import edu.icet.clothifybackend.service.config.ItemMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final StockRepository stockRepository;
    private final ItemRepository itemRepository;
    private final ItemMapper mapper;

    @Override
    public ItemDto createItem(ItemDto dto) {
        //check if stockId in database
        //If unavailable, throw Exception
        StockEntity stockEntity = stockRepository.getStockByStockId(dto.getStockId())
                .orElseThrow(()-> new StockIdNotFoundException(dto.getStockId()));

        ItemEntity itemEntity = mapper.convertDtoToEntity(dto, stockEntity);

        //item saving in database
        stockEntity.getItemList().add(itemEntity);
        StockEntity savedStockEntity = stockRepository.save(stockEntity);

        //get latest saved entity and convert into dto and return
        ItemEntity savedItemEntity = savedStockEntity.getItemList().get(savedStockEntity.getItemList().size()-1);

        return mapper.convertEntityToDto(savedItemEntity);
    }

    @Override
    public ItemDto getItemByItemId(Long itemId) {
        //finds whether item available
        //if not, throw Exception
        ItemEntity itemEntity = itemRepository.getItemByItemId(itemId)
                .orElseThrow(()-> new ItemIdNotFoundException(itemId));

        return mapper.convertEntityToDto(itemEntity);
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<ItemEntity> entityList = itemRepository.getAllItems();
        Iterator<ItemEntity> iterator = entityList.iterator();

        //convert entities into dto and add one by one
        ArrayList<ItemDto> dtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            ItemDto dto = mapper.convertEntityToDto(iterator.next());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public Long deleteItemById(Long itemId) {
        //check id available the database
        if (itemRepository.getItemByItemId(itemId).isEmpty()) {
            throw new ItemIdNotFoundException(itemId);
        }
        itemRepository.deleteById(itemId);
        return itemId;
    }

    @Override
    public ItemDto updateItem(ItemDto dto) {
        //check whether stockId of ItemDto in database
        StockEntity stockEntity = stockRepository.getStockByStockId(dto.getStockId())
                .orElseThrow(()-> new StockIdNotFoundException(dto.getStockId()));

        //check whether itemId of ItemDto in database
        if(itemRepository.getItemByItemId(dto.getItemId()).isEmpty()){
                throw new ItemIdNotFoundException(dto.getItemId());
        }

        //convert dto into entity and update
        ItemEntity itemEntity = itemRepository.save(mapper.convertDtoToEntity(dto, stockEntity));

        //convert entity into dto and return
        return mapper.convertEntityToDto(itemEntity);
    }
}

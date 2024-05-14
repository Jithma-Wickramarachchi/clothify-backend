package edu.icet.clothifybackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.StockDto;
import edu.icet.clothifybackend.entity.StockEntity;
import edu.icet.clothifybackend.exception.StockIdNotFoundException;
import edu.icet.clothifybackend.repository.StockRepository;
import edu.icet.clothifybackend.service.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;
    private final ObjectMapper mapper;

    @Override
    public StockDto saveStock(StockDto dto) {
        StockEntity savedEntity = stockRepository.save(mapper.convertValue(dto, StockEntity.class));
        return mapper.convertValue(savedEntity, StockDto.class);
    }

    @Override
    public StockDto getStockByStockId(Long stockId) {
        StockEntity entity = stockRepository.getStockByStockId(stockId)
                .orElseThrow(()-> new StockIdNotFoundException(stockId));

        return mapper.convertValue(entity, StockDto.class);
    }

    @Override
    public List<StockDto> getAllStocks() {
        Iterable<StockEntity> entityList = stockRepository.getAllStocks();
        Iterator<StockEntity> iterator = entityList.iterator();

        //convert entities into dto and add one by one
        ArrayList<StockDto> dtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            dtoList.add(mapper.convertValue(iterator.next(), StockDto.class));
        }
        return dtoList;
    }

    @Override
    public Long deleteStockById(Long stockId) {

        //check id is in the database
        if (stockRepository.getStockByStockId(stockId).isEmpty()) {
            throw new StockIdNotFoundException(stockId);
        }
        stockRepository.deleteById(stockId);
        return stockId;
    }

    @Override
    public StockDto updateStock(StockDto dto) {

        //check whether id of StockDto in database
        if (stockRepository.getStockByStockId(dto.getStockId()).isEmpty()) {
            throw new StockIdNotFoundException(dto.getStockId());
        }

        //convert dto into entity and update
        StockEntity stockEntity = stockRepository.save(mapper.convertValue(dto, StockEntity.class));

        //convert entity into dto and return
        return mapper.convertValue(stockEntity, StockDto.class);
    }
}

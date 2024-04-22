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
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StockServiceImpl implements StockService {
    private final StockRepository repository;
    private final ObjectMapper mapper;

    @Override
    public StockDto saveStock(StockDto dto) {
        StockEntity savedEntity = repository.save(mapper.convertValue(dto, StockEntity.class));
        return mapper.convertValue(savedEntity, StockDto.class);
    }

    @Override
    public StockDto getStockByStockId(Long stockId) {
        Optional<StockDto> dto = Optional.ofNullable(
                mapper.convertValue(repository.findById(stockId), StockDto.class));

        //if dto unavailable, throws the Exception
        return dto.orElseThrow(() ->
                new StockIdNotFoundException(stockId));
    }

    @Override
    public List<StockDto> getAllStocks() {
        Iterable<StockEntity> entityList = repository.findAll();
        Iterator<StockEntity> iterator = entityList.iterator();

        //convert entities into dto and add one by one
        ArrayList<StockDto> dtoList = new ArrayList<>();
        while (iterator.hasNext()) {
            dtoList.add(mapper.convertValue(iterator.next(), StockDto.class));
        }
        return dtoList;
    }

    @Override
    public void deleteStockById(Long stockId) {

        //check id is in the database
        if (repository.findById(stockId).isEmpty()) {
            throw new StockIdNotFoundException(stockId);
        }
        repository.deleteById(stockId);
    }

    @Override
    public StockDto updateStock(StockDto dto) {

        //check whether id of StockDto in database
        if (repository.findById(dto.getStockId()).isEmpty()) {
            throw new StockIdNotFoundException(dto.getStockId());
        }

        //convert dto into entity and update
        StockEntity stockEntity = repository.save(mapper.convertValue(dto, StockEntity.class));

        //convert entity into dto and return
        return mapper.convertValue(stockEntity, StockDto.class);
    }
}

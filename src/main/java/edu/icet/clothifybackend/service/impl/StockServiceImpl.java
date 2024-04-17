package edu.icet.clothifybackend.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothifybackend.dto.StockDto;
import edu.icet.clothifybackend.entity.StockEntity;
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
    public Optional<StockDto> getStockByStockId(Long stockId) {
        return Optional.ofNullable(mapper.convertValue(repository.findById(stockId), StockDto.class));
    }

    @Override
    public List<StockDto> getAllStocks() {
        Iterable<StockEntity> entityList = repository.findAll();
        Iterator<StockEntity> iterator = entityList.iterator();

        ArrayList<StockDto> dtoList = new ArrayList<>();

        while (iterator.hasNext()){
           dtoList.add(mapper.convertValue(iterator.next(), StockDto.class));
        }
        return dtoList;
    }

    @Override
    public Boolean deleteStockById(Long stockId) {
        if (repository.findById(stockId).isPresent()){
            repository.deleteById(stockId);
            return true;
        }
        return false;
    }

    @Override
    public Optional<StockDto> updateStock(StockDto dto) {
        StockEntity savedEntity = repository.save(mapper.convertValue(dto, StockEntity.class));
        return Optional.ofNullable(mapper.convertValue(savedEntity, StockDto.class));
    }
}

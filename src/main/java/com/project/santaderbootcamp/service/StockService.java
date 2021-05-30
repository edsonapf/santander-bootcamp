package com.project.santaderbootcamp.service;

import com.project.santaderbootcamp.exceptions.BusinessException;
import com.project.santaderbootcamp.mapper.StockMapper;
import com.project.santaderbootcamp.model.Stock;
import com.project.santaderbootcamp.model.dto.StockDTO;
import com.project.santaderbootcamp.repository.StockRepository;
import com.project.santaderbootcamp.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    @Autowired
    private StockMapper mapper;

    @Transactional
    public StockDTO save(StockDTO dto) {

        Optional<Stock> optionalStock = repository.findByNameAndDate(dto.getName(), dto.getDate());

        if(optionalStock.isPresent()) {
            throw new BusinessException(MessageUtils.STOCK_ALREADY_EXISTS);
        }

        Stock stock = mapper.toEntity(dto);

        repository.save(stock);

        return mapper.toDto(stock);
    }
}

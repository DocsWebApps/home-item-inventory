package com.docswebapps.homeiteminventory.service.impl;

import com.docswebapps.homeiteminventory.domain.ItemLocationEntity;
import com.docswebapps.homeiteminventory.repository.ItemLocationRepository;
import com.docswebapps.homeiteminventory.service.ItemLocationService;
import com.docswebapps.homeiteminventory.exception.EntryAlreadyExistsException;
import com.docswebapps.homeiteminventory.service.mapper.ItemLocationMapper;
import com.docswebapps.homeiteminventory.web.dto.ItemLocationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemLocationServiceImpl implements ItemLocationService {
    private final ItemLocationRepository itemLocationRepository;
    private final ItemLocationMapper itemLocationMapper;

    @Autowired
    public ItemLocationServiceImpl(ItemLocationRepository locationRepository, ItemLocationMapper itemLocationMapper) {
        this.itemLocationRepository = locationRepository;
        this.itemLocationMapper = itemLocationMapper;
    }

    @Override
    public List<ItemLocationDto> getAllLocations() {
        log.info("ItemLocationServiceImpl: getAllLocations() called");
        return this.itemLocationRepository.findAll().stream()
            .map(itemLocationMapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public ItemLocationDto getLocationById(Long id) {
        log.info("ItemLocationServiceImpl: getLocationById() called with: {}", id);
        return this.itemLocationRepository.findById(id)
            .map(itemLocationMapper::entityToDto).orElse(null);
    }

    @Override
    public Long createLocation(ItemLocationDto itemLocationDto) throws Exception {
        try {
            log.info("ItemLocationServiceImpl: createLocation() called with: {}", itemLocationDto.toString());
            ItemLocationEntity itemLocationEntity = itemLocationMapper.dtoToEntity(itemLocationDto);
            return this.itemLocationRepository.save(itemLocationEntity).getId();
        }  catch(DataIntegrityViolationException e){
            throw new EntryAlreadyExistsException(itemLocationDto.getName());
        }
    }
}

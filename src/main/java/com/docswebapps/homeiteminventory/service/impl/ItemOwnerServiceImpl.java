package com.docswebapps.homeiteminventory.service.impl;

import com.docswebapps.homeiteminventory.domain.ItemOwnerEntity;
import com.docswebapps.homeiteminventory.exception.EntryAlreadyExistsException;
import com.docswebapps.homeiteminventory.repository.ItemOwnerRepository;
import com.docswebapps.homeiteminventory.service.ItemOwnerService;
import com.docswebapps.homeiteminventory.service.mapper.ItemOwnerMapper;
import com.docswebapps.homeiteminventory.web.dto.ItemOwnerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ItemOwnerServiceImpl implements ItemOwnerService {
    private final ItemOwnerRepository itemOwnerRepository;
    private final ItemOwnerMapper itemOwnerMapper;

    public ItemOwnerServiceImpl(ItemOwnerRepository itemOwnerRepository, ItemOwnerMapper itemOwnerMapper) {
        this.itemOwnerRepository = itemOwnerRepository;
        this.itemOwnerMapper = itemOwnerMapper;
    }

    @Override
    public List<ItemOwnerDto> getAllOwners() {
        log.info("ItemOwnerServiceImpl: getAllOwners() called");
        return this.itemOwnerRepository.findAll().stream()
            .map(itemOwnerMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public ItemOwnerDto getOwnerById(Long id) {
        log.info("ItemOwnerServiceImpl: getOwnerById() called with id: {}", id);
        return this.itemOwnerRepository.findById(id)
            .map(itemOwnerMapper::entityToDto)
            .orElseGet(() -> null);
    }

    @Override
    public Long createOwner(ItemOwnerDto itemOwnerDto) throws EntryAlreadyExistsException {
        log.info("ItemOwnerServiceImpl: createOwner() called with Dto: {}", itemOwnerDto.toString());
        try {
            ItemOwnerEntity itemOwnerEntity = itemOwnerMapper.dtoToEntity(itemOwnerDto);
            return this.itemOwnerRepository.save(itemOwnerMapper.dtoToEntity(itemOwnerDto)).getId();
        } catch (DataIntegrityViolationException ex) {
            throw new EntryAlreadyExistsException(itemOwnerDto.getName());
        }
    }

    @Override
    public boolean updateOwner(Long id, ItemOwnerDto itemOwnerDto) {
        return false;
    }

    @Override
    public boolean deleteOwner(Long id) {
        return false;
    }
}

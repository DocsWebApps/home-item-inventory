package com.docswebapps.homeiteminventory.service;

import com.docswebapps.homeiteminventory.exception.EntryAlreadyExistsException;
import com.docswebapps.homeiteminventory.web.dto.ItemOwnerDto;

import java.util.List;

public interface ItemOwnerService {
    List<ItemOwnerDto> getAllOwners();
    ItemOwnerDto getOwnerById(Long id);
    Long createOwner(ItemOwnerDto itemOwnerDto) throws EntryAlreadyExistsException;
    boolean updateOwner(Long id, ItemOwnerDto itemOwnerDto);
    boolean deleteOwner(Long id);

}

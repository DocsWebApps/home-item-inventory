package com.docswebapps.homeiteminventory.service;

import com.docswebapps.homeiteminventory.web.dto.ItemLocationDto;
import java.util.List;

public interface ItemLocationService {
    List<ItemLocationDto> getAllLocations();
    ItemLocationDto getLocationById(Long id);
    Long createLocation(ItemLocationDto itemLocationDto) throws Exception;
}

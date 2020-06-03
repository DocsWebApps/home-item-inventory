package com.docswebapps.homeiteminventory.service.mapper;

import com.docswebapps.homeiteminventory.domain.ItemLocationEntity;
import com.docswebapps.homeiteminventory.web.dto.ItemLocationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
public interface ItemLocationMapper {

    ItemLocationDto entityToDto(ItemLocationEntity itemLocationEntity);

    @Mapping(target = "version", ignore = true)
    ItemLocationEntity dtoToEntity (ItemLocationDto itemLocationDto);
}

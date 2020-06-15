package com.docswebapps.homeiteminventory.service.mapper;

import com.docswebapps.homeiteminventory.domain.ItemOwnerEntity;
import com.docswebapps.homeiteminventory.web.dto.ItemOwnerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = DateMapper.class)
public interface ItemOwnerMapper {
    ItemOwnerDto entityToDto(ItemOwnerEntity itemOwnerEntity);

    @Mapping(target = "version", ignore = true)
    ItemOwnerEntity dtoToEntity(ItemOwnerDto itemOwnerDto);
}

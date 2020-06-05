package com.docswebapps.homeiteminventory;

import com.docswebapps.homeiteminventory.domain.ItemLocationEntity;
import com.docswebapps.homeiteminventory.web.dto.ItemLocationDto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class BaseDataTest {
    public ItemLocationDto itemLocationDto = ItemLocationDto.builder().name("Test Name").build();

    public List<ItemLocationEntity> itemLocationEntities = Arrays.asList(
            ItemLocationEntity.builder()
                .id(1L)
                .name("TestLocation One")
                .createdAt(Timestamp.valueOf(LocalDateTime.now().minusDays(2)))
                .lastModifiedAt(Timestamp.valueOf(LocalDateTime.now().minusDays(1)))
                .version(1L)
                .build(),
            ItemLocationEntity.builder()
                .id(2L)
                .name("TestLocation Two")
                .createdAt(Timestamp.valueOf(LocalDateTime.now().minusDays(5)))
                .lastModifiedAt(Timestamp.valueOf(LocalDateTime.now().minusDays(3)))
                .version(5L)
                .build()
            );
}

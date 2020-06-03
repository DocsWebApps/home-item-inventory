package com.docswebapps.homeiteminventory.web.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class BaseDto {
    @Null
    private Long id;

    @Null
    private OffsetDateTime createdAt;

    @Null
    private OffsetDateTime lastModifiedAt;
}

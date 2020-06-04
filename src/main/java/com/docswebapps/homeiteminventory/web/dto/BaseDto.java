package com.docswebapps.homeiteminventory.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Null;
import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseDto {
    @Null
    private Long id;

    @Null
    private OffsetDateTime createdAt;

    @Null
    private OffsetDateTime lastModifiedAt;
}

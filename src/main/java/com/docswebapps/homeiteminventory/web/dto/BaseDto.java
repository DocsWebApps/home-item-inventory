package com.docswebapps.homeiteminventory.web.dto;

import javax.validation.constraints.Null;
import java.time.OffsetDateTime;

public class BaseDto {
    @Null
    private Long id;

    @Null
    private OffsetDateTime createdAt;

    @Null
    private OffsetDateTime lastModifiedAt;
}

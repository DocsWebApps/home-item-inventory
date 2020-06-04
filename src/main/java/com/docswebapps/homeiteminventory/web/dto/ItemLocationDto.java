package com.docswebapps.homeiteminventory.web.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemLocationDto extends BaseDto {
    @NotBlank
    @NotNull
    private String name;

    @Builder
    public ItemLocationDto(Long id, OffsetDateTime createdAt, OffsetDateTime lastModifiedAt, String name) {
        super(id, createdAt, lastModifiedAt);
        this.name = name;
    }
}

package com.docswebapps.homeiteminventory.web.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class ItemOwnerDto extends BaseDto {
    @NotBlank
    @NotNull
    private String name;

    @Builder
    public ItemOwnerDto(Long id, OffsetDateTime createdAt, OffsetDateTime lastModifiedAt, String name) {
        super(id, createdAt, lastModifiedAt);
        this.name = name;
    }
}

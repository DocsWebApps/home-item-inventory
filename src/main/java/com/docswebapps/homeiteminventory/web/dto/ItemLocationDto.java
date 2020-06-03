package com.docswebapps.homeiteminventory.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemLocationDto extends BaseDto{
    @NotBlank
    @NotNull
    private String name;
}

/*
{"name":"Lounge"}
 */

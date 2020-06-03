package com.docswebapps.homeiteminventory.exception;

import lombok.Getter;

@Getter
public class EntryAlreadyExistsException extends Exception {
    private final String entry;

    public EntryAlreadyExistsException(String entry) {
        this.entry = entry;
    }
}

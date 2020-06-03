package com.docswebapps.homeiteminventory.web.rest.v1;

import com.docswebapps.homeiteminventory.service.ItemLocationService;
import com.docswebapps.homeiteminventory.exception.EntryAlreadyExistsException;
import com.docswebapps.homeiteminventory.web.dto.ItemLocationDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/location")
@Slf4j
public class ItemLocationRestController {
    private final ItemLocationService itemLocationService;

    @Autowired
    public ItemLocationRestController(@Qualifier("itemLocationServiceImpl") ItemLocationService itemLocationService) {
        this.itemLocationService = itemLocationService;
    }

    @GetMapping
    public ResponseEntity<List<ItemLocationDto>> getAllLocations() {
        log.info("ItemLocationRestController: getAllLocations() called");
        List<ItemLocationDto> itemLocations =  this.itemLocationService.getAllLocations();
        return  itemLocations.isEmpty()
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok().body(itemLocations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemLocationDto> getLocationById(@PathVariable("id") Long id) {
        log.info("ItemLocationRestController: getLocationById() called with id: {}", id);
        ItemLocationDto itemLocation = this.itemLocationService.getLocationById(id);
        return itemLocation == null
            ? ResponseEntity.notFound().build()
            : ResponseEntity.ok().body(itemLocation);
    }

    @PostMapping
    public ResponseEntity<String> createLocation(@Valid @RequestBody ItemLocationDto itemLocationDto) throws Exception {
        log.info("ItemLocationRestController: createLocation() called with id: {}", itemLocationDto.toString());
        try {
            Long id = this.itemLocationService.createLocation(itemLocationDto);
            URI uri = new URI("/api/v1/location/" + id);
            return ResponseEntity.created(uri).build();
        } catch(EntryAlreadyExistsException ex) {
            log.info("EntryAlreadyExistsException: Entry "+ ex.getEntry() + " already exists!");
            return ResponseEntity.badRequest().body("Entry "+ ex.getEntry() + " already exists!");
        }
    }
}
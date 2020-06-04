package com.docswebapps.homeiteminventory.service.impl;

import com.docswebapps.homeiteminventory.BaseDataTest;
import com.docswebapps.homeiteminventory.exception.EntryAlreadyExistsException;
import com.docswebapps.homeiteminventory.repository.ItemLocationRepository;
import com.docswebapps.homeiteminventory.service.mapper.ItemLocationMapper;
import com.docswebapps.homeiteminventory.web.dto.ItemLocationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemLocationServiceImplTest extends BaseDataTest {
    @Mock
    ItemLocationMapper itemLocationMapper;

    @Mock
    ItemLocationRepository itemLocationRepository;

    @InjectMocks
    ItemLocationServiceImpl itemLocationService;

    @Test
    void getAllLocations() {
        List<ItemLocationDto> expected = this.itemLocationEntities.stream()
                .map(itemLocationMapper::entityToDto).collect(Collectors.toList());
        // Happy Path
        when(this.itemLocationRepository.findAll()).thenReturn(this.itemLocationEntities);
        assertEquals(expected, this.itemLocationService.getAllLocations());

        // Sad Path - return no entities
        when(this.itemLocationRepository.findAll()).thenReturn(new ArrayList<>());
        assertEquals(true, this.itemLocationService.getAllLocations().isEmpty());
    }

    @Test
    void getLocationById() {
        // Happy Path
        ItemLocationDto expected = itemLocationMapper.entityToDto(this.itemLocationEntities.get(0));
        when(this.itemLocationRepository.findById(anyLong())).thenReturn(Optional.of(this.itemLocationEntities.get(0)));
        assertEquals(expected, this.itemLocationService.getLocationById(anyLong()));

        // Sad Path
        when(this.itemLocationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertNull(this.itemLocationService.getLocationById(anyLong()));
    }

    @Test
    void createLocation() throws Exception {
        // Happy Path
        Long expected = this.itemLocationEntities.get(0).getId();
        when(this.itemLocationRepository.save(any())).thenReturn(this.itemLocationEntities.get(0));
        assertEquals(expected, this.itemLocationService.createLocation(this.itemLocationDto));

        // Sad Path
        when(this.itemLocationRepository.save(any())).thenThrow(new DataIntegrityViolationException(this.itemLocationDto.getName()));
        assertThrows(EntryAlreadyExistsException.class, () -> this.itemLocationService.createLocation(this.itemLocationDto));
    }

    @Test
    void updateLocation() {
        // Happy Path
        when(this.itemLocationRepository.findById(anyLong())).thenReturn(Optional.of(this.itemLocationEntities.get(0)));
        assertTrue(this.itemLocationService.updateLocation(anyLong(),this.itemLocationDto));

        // Sad Path
        when(this.itemLocationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertFalse(this.itemLocationService.updateLocation(anyLong(),this.itemLocationDto));
    }

    @Test
    void deleteLocation() {
        // Happy Path
        when(this.itemLocationRepository.findById(anyLong())).thenReturn(Optional.of(this.itemLocationEntities.get(0)));
        assertTrue(this.itemLocationService.deleteLocation(anyLong()));

        // Sad Path
        when(this.itemLocationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertFalse(this.itemLocationService.deleteLocation(anyLong()));
    }
}
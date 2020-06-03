package com.docswebapps.homeiteminventory.web.rest.v1;

import com.docswebapps.homeiteminventory.exception.EntryAlreadyExistsException;
import com.docswebapps.homeiteminventory.service.ItemLocationService;
import com.docswebapps.homeiteminventory.web.dto.ItemLocationDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ItemLocationRestController.class)
class ItemLocationRestControllerTest extends BaseRestTest {
    private final static String API_URL="/api/v1/location";

    @MockBean
    @Qualifier("itemLocationServiceImpl")
    ItemLocationService itemLocationService;

    @Override
    @Test
    void createResource() throws Exception {
        String json = this.objectMapper.writeValueAsString(this.itemLocationDto);
        // Happy Path
        when(this.itemLocationService.createLocation(any())).thenReturn(101L);
        this.mockMvc.perform(post(this.API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated());
        // Sad Path - location already exists
        when(this.itemLocationService.createLocation(any())).thenThrow(new EntryAlreadyExistsException(itemLocationDto.getName()));
        this.mockMvc.perform(post(this.API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andExpect( result -> {
                assertEquals("Entry "+ itemLocationDto.getName() + " already exists!", result.getResponse().getContentAsString());
            });
    }

    @Override
    @Test
    void updateResourceById() throws Exception  {
        String json = this.objectMapper.writeValueAsString(itemLocationDto);
        // Happy Path
        when(this.itemLocationService.updateLocation(anyLong(), any())).thenReturn(true);
        this.mockMvc.perform(put(this.API_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isNoContent());
        // Sad Path
        when(this.itemLocationService.updateLocation(anyLong(),any())).thenReturn(false);
        this.mockMvc.perform(put(this.API_URL + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isBadRequest())
            .andExpect(result -> {
                assertEquals("Error updating Location. Contact an administrator!", result.getResponse().getContentAsString());
            });
    }

    @Override
    @Test
    void deleteResourceById() throws Exception {
        // Happy Path
        when(this.itemLocationService.deleteLocation(anyLong())).thenReturn(true);
        this.mockMvc.perform(delete(this.API_URL + "/1"))
            .andExpect(status().isNoContent());
        // Sad Path
        when(this.itemLocationService.deleteLocation(anyLong())).thenReturn(false);
        this.mockMvc.perform(delete(this.API_URL + "/1"))
            .andExpect(status().isBadRequest())
            .andExpect(result -> {
                assertEquals("Error deleting Location. Contact an administrator!", result.getResponse().getContentAsString());
            });
    }

    @Override
    @Test
    void getAllResources() throws Exception {
        // Happy Path
        when(this.itemLocationService.getAllLocations()).thenReturn(Collections.singletonList(ItemLocationDto.builder().build()));
        this.mockMvc.perform(get(this.API_URL)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        // Sad Path
        when(this.itemLocationService.getAllLocations()).thenReturn(new ArrayList<>());
        this.mockMvc.perform(get(this.API_URL)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Override
    @Test
    void getResourceById() throws Exception {
        // Happy Path
        when(this.itemLocationService.getLocationById(anyLong())).thenReturn(ItemLocationDto.builder().build());
        this.mockMvc.perform(get(this.API_URL + "/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        // Sad Path
        when(this.itemLocationService.getLocationById(anyLong())).thenReturn(null);
        this.mockMvc.perform(get(this.API_URL + "/1")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
}
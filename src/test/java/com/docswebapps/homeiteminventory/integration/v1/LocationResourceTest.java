package com.docswebapps.homeiteminventory.integration.v1;

import com.docswebapps.homeiteminventory.repository.ItemLocationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LocationResourceTest extends BaseIntegrationTest {
    private final static String API_URL="/api/v1/location";

    @Autowired
    ItemLocationRepository itemLocationRepository;

    @BeforeEach
    public void loadData() {
        this.itemLocationEntities.forEach(itemLocationEntity -> {
            this.itemLocationRepository.save(itemLocationEntity);
        });
        this.rowCountBefore = this.itemLocationRepository.findAll().size();
    }

    @AfterEach
    public void deleteData() {
        this.itemLocationRepository.deleteAll();
    }

    @Override
    @Test
    void createResource() throws Exception {
        assertEquals(this.rowCountBefore, this.itemLocationRepository.findAll().size());
        String json = this.objectMapper.writeValueAsString(this.itemLocationDto);

        this.mockMvc.perform(post(this.API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated());

        this.rowCountAfter = this.itemLocationRepository.findAll().size();
        assertEquals(this.rowCountBefore + 1, this.rowCountAfter);
    }

    @Override
    @Test
    void updateResource() throws Exception {
        assertEquals(this.rowCountBefore, this.itemLocationRepository.findAll().size());

        this.itemLocationRepository.findAll().stream()
                .forEach(itemLocationEntity -> {
                    try {
                        this.itemLocationDto.setName("Test" + itemLocationEntity.getId());
                        String json = this.objectMapper.writeValueAsString(this.itemLocationDto);

                        this.mockMvc.perform(put(this.API_URL + "/" + itemLocationEntity.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json))
                                .andExpect(status().isNoContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        assertEquals(this.rowCountBefore, this.itemLocationRepository.findAll().size());
    }

    @Override
    @Test
    void deleteResource() throws Exception {
        assertEquals(this.rowCountBefore, this.itemLocationRepository.findAll().size());

        this.itemLocationRepository.findAll().stream()
                .forEach(itemLocationEntity -> {
                    try {
                        this.mockMvc.perform(delete(this.API_URL + "/" + itemLocationEntity.getId()))
                            .andExpect(status().isNoContent());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        assertEquals(0, this.itemLocationRepository.findAll().size());
    }

    @Override
    @Test
    void getAllResources() throws Exception {
        assertEquals(this.rowCountBefore, this.itemLocationRepository.findAll().size());
        String locationName1 = this.itemLocationEntities.get(0).getName();
        String locationName2 = this.itemLocationEntities.get(1).getName();

        this.mockMvc.perform(get(this.API_URL)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.[*].name").value(hasItem(locationName1)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(locationName1)));

        assertEquals(this.rowCountBefore, this.itemLocationRepository.findAll().size());
    }

    @Override
    @Test
    void getResource() throws Exception {
        assertEquals(this.rowCountBefore, this.itemLocationRepository.findAll().size());
        this.itemLocationRepository.findAll().stream()
            .forEach(itemLocationEntity -> {
                try {
                    this.mockMvc.perform(get(this.API_URL + "/" + itemLocationEntity.getId())
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.name").value(itemLocationEntity.getName()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        assertEquals(this.rowCountBefore, this.itemLocationRepository.findAll().size());
    }

    @Test
    @Override
    void handleMANVExceptionTest() throws Exception {
        this.itemLocationDto.setName("");
        String json = this.objectMapper.writeValueAsString(this.itemLocationDto);

        this.mockMvc.perform(post(this.API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertEquals("[\"MethodArgumentNotValidException: name - must not be blank\"]", result.getResponse().getContentAsString());
                });

        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("One");
        });
    }

    @Test
    @Override
    void handleHMNREExceptionTest() throws Exception {
        String json = this.objectMapper.writeValueAsString(null);

        this.mockMvc.perform(post(this.API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(result -> {
                    assertTrue(result.getResponse().getContentAsString().contains("HttpMessageNotReadableException:"));
                });

        Assertions.assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("One");
        });
    }
}

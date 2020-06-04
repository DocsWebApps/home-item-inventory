package com.docswebapps.homeiteminventory.integration.v1;

import com.docswebapps.homeiteminventory.BaseDataTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseIntegrationTest extends BaseDataTest {
    int rowCountBefore, rowCountAfter = 0;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    EntityManager entityManager;

    abstract void createResource() throws Exception;
    abstract void updateResource() throws Exception;
    abstract void deleteResource() throws Exception;
    abstract void getAllResources() throws Exception;
    abstract void getResource() throws Exception;
    abstract void handleMANVExceptionTest() throws Exception;
    abstract void handleHMNREExceptionTest() throws Exception;

}

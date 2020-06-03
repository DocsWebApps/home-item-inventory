package com.docswebapps.homeiteminventory.web.rest.v1;

import com.docswebapps.homeiteminventory.BaseDataTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public abstract class BaseRestTest extends BaseDataTest {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    abstract void createResource() throws Exception;
    abstract void updateResourceById() throws Exception;
    abstract void deleteResourceById() throws Exception;
    abstract void getAllResources() throws Exception;
    abstract void getResourceById() throws Exception;
}

package com.docswebapps.homeiteminventory.web.rest.v1;

import com.docswebapps.homeiteminventory.web.BaseWebTest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseV1RestTest extends BaseWebTest {

    @Autowired
    ObjectMapper objectMapper;

    abstract void createResource() throws Exception;
    abstract void updateResourceById() throws Exception;
    abstract void deleteResourceById() throws Exception;
    abstract void getAllResources() throws Exception;
    abstract void getResourceById() throws Exception;
}

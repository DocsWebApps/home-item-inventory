package com.docswebapps.homeiteminventory.web.controller;

import com.docswebapps.homeiteminventory.web.BaseWebTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomePageController.class)
public class HomePageControllerTest extends BaseWebTest {

    @Test
    void testReturnHomePage() throws Exception {
        mockMvc.perform(get("/").with(httpBasic("user", "user")))
            .andExpect(status().isOk())
            .andExpect(view().name("homePage"))
            .andExpect(model().attributeExists("homeTitle"));
    }

}
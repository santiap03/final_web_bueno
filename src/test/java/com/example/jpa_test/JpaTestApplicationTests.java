package com.example.jpa_test;

import Model.Cliente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
class JpaTestApplicationTests {
    @Autowired
    MockMvc mock;
    @Test
    @WithMockUser(username = "admin", password = "admin" ,authorities = { "ADMIN", "USER" })
    void testListaClientesAuth() throws Exception {
        MvcResult result =  mock.perform(get("/clientes"))/*.andDo(print())*/.andReturn();//;
        System.out.println("El resultado de la prueba fue: \n"+ result.getResponse().getContentAsString());
        //System.out.println("El tipo de variable essss:     "+result.getResponse().getErrorMessage().getClass());
        assertNull( result.getResponse().getErrorMessage());

    }
    @Test

    void testListaClientes() throws Exception {
        MvcResult result =  mock.perform(get("/clientes"))/*.andDo(print())*/.andReturn();//;
        System.out.println("El resultado fue:    \n"+result.getResponse().getErrorMessage());
        assertNotNull( result.getResponse().getErrorMessage());

    }

    @Test
    @WithMockUser(username = "admin", password = "admin" ,authorities = { "ADMIN", "USER" })
    public void whenFileUploaded_thenVerifyStatus() throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("image", "filename.txt", "text/plain", "some xml".getBytes());


        MvcResult result= mock.perform(MockMvcRequestBuilders.multipart("/photos/add")
                        .file(firstFile)
                        .param("title", "23")).andDo(print()).andReturn();

    }
}

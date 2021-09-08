package com.example.jpa_test;

import Model.Cliente;
import org.junit.jupiter.api.Order;
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


import java.util.List;

import static java.lang.Boolean.parseBoolean;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Test//Test add image
    @WithMockUser(username = "admin", password = "admin" ,authorities = { "ADMIN", "USER" })
    public void whenFileUploaded_thenVerifyStatus() throws Exception {
        MockMultipartFile firstFile = new MockMultipartFile("image", "filename.txt", "text/plain", "some xml".getBytes());


        MvcResult result= mock.perform(MockMvcRequestBuilders.multipart("/photos/add")
                        .file(firstFile)
                        .param("title", "23")).andDo(print()).andReturn();

    }
    @Test
    @WithMockUser(username = "admin", password = "admin" ,authorities = { "ADMIN", "USER" })
    void testClientebyid() throws Exception {
        int[] numeros= {1,2,3,66};
        for (int a: numeros) {
            MvcResult result =  mock.perform(get("/clientes/"+a))/*.andDo(print())*/.andReturn();//;
            assertNull( result.getResponse().getErrorMessage());
            System.out.println("El resultado de la prueba fue con id_"+a+"fue:   \n"+ result.getResponse().getContentAsString());
        }

    }
    @Test
    @Order(6)
    @WithMockUser(username = "admin", password = "admin" ,authorities = { "ADMIN", "USER" })
    void testAgregar() throws Exception{
        MvcResult result= mock.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idCliente\":124," +
                        "\"nombres\":\"sebastian\"," +
                        "\"apellidos\":\"ortega\"," +
                        "\"ciudad\":\"medellin\"," +
                        "\"edad\":40," +
                        "\"doctype\":\"tarde\"}")
        ).andDo(print()).andReturn();
        System.out.println("El resultado de la prueba de agregar cliente fue:   \n"+ result.getResponse().getContentAsString());
        assertTrue(parseBoolean(result.getResponse().getContentAsString()));

    }
    @Test
    @Order(7)
    @WithMockUser(username = "admin", password = "admin" ,authorities = { "ADMIN", "USER" })
    void testAgregar2() throws Exception{
        MvcResult result= mock.perform(post("/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"idCliente\":123," +
                        "\"nombres\":\"sebastian\"," +
                        "\"apellidos\":\"ortega\"," +
                        "\"ciudad\":\"medellin\"," +
                        "\"edad\":40," +
                        "\"doctype\":\"tarde\"}")
        ).andDo(print()).andReturn();
        System.out.println("El resultado de la prueba de agregar cliente fue:   \n"+ result.getResponse().getContentAsString());
        assertFalse(parseBoolean(result.getResponse().getContentAsString()));


    }
    @Test
    @Order(2)
    @WithMockUser(username = "admin", password = "admin" ,authorities = { "ADMIN", "USER" })
    void testClientemayor() throws Exception {
        int[] numeros= {1,20,25};
        for (int a: numeros) {
            MvcResult result =  mock.perform(get("/clientes/mayores/"+a))/*.andDo(print())*/.andReturn();//;
            assertNull( result.getResponse().getErrorMessage());
            System.out.println("El resultado de la prueba de clientes mayores a_"+a+"años fue:   \n"+ result.getResponse().getContentAsString());
        }

    }
    @Test
    @Order(1)
    @WithMockUser(username = "admin", password = "admin" ,authorities = { "ADMIN", "USER" })
    void testObtenerimagentitulo() throws Exception {
        int[] numeros= {66,23,21};
        for (int a: numeros) {
            MvcResult result =  mock.perform(get("/photo/"+a))/*.andDo(print())*/.andReturn();//;
            assertNull( result.getResponse().getErrorMessage());
            System.out.println("El resultado de la prueba de foto de cliente de id"+a+"  fue:   \n"+ result.getResponse().getContentAsString());
        }

    }

}

package com.assignment.retailerreward;

import com.assignment.retailerreward.controller.PointController;
import com.assignment.retailerreward.dto.ResponseDto;
import com.assignment.retailerreward.entity.Customer;
import com.assignment.retailerreward.entity.Transaction;
import com.assignment.retailerreward.repo.TransactionRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//L!m3l1g#t2020

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(PointController.class)
//@ActiveProfiles("test")
public class PointControllerTest {

    /**
     * @MockMVCTest : annotation is used for Spring MVC tests. It disables full auto-configuration
     * and instead apply only configuration relevant to MVC tests.
     */

    /**
     * @MockMvc : is a class part of Spring MVC Test which help you to test controllers
     * explicitly starting a Servlet container.
     * and then, we create dummy data on funDtoList.
     */
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private TransactionRepo transactionRepo;


    /**
     * Actually we are testing controller. So controller is dependent on Service
     * So MockBean mocks the service for testing purpose
     */

    private ObjectMapper objectMapper = new ObjectMapper();
    List<Transaction> transactionList = new ArrayList<>();

    @BeforeEach
    void setUpObject() {
        log.info("Mocking transaction before test");
        transactionList.add(new Transaction(1, new Customer(1, "ironman", "8147851654", "ironman@yopmail.com"), 120F));
    }


    @Test
    void pointCalculatorTest() throws Exception {
        Mockito.when(transactionRepo.findAllByCustomerId(1)).thenReturn(transactionList);
        RequestBuilder request = MockMvcRequestBuilders.get("/customer-id/1").accept(MediaType.APPLICATION_JSON);

        MvcResult mvcResult = mockMvc.perform(request).andExpect(status().isOk()).andExpect(content()
                .contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.status", Is.is(true))).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        ResponseDto responseDto = objectMapper.readValue(response, ResponseDto.class);
        LinkedHashMap linkedHashMap = (LinkedHashMap) responseDto.getData();
        Assertions.assertTrue(linkedHashMap.get("customerId").equals(1));
        Assertions.assertTrue(linkedHashMap.get("totalPoints").equals(90));
    }

    /**
     * This will execute after all the test executes
     * If you have to destroy any predefined things you can destroy here
     */
    @AfterAll
    public static void displayTestCompletionMessage() {
        log.info("Test completed");
    }
}
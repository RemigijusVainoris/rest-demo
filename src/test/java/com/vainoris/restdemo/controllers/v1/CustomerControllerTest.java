package com.vainoris.restdemo.controllers.v1;

import com.vainoris.restdemo.api.v1.model.CustomerDTO;
import com.vainoris.restdemo.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest
{
    private static final String CUSTOMER_ONE_FIRST_NAME = "John";
    private static final String CUSTOMER_ONE_LAST_NAME = "Doe";
    private static final String CUSTOMER_TWO_FIRST_NAME = "Jane";
    private static final String CUSTOMER_TWO_LAST_NAME = "Doe";

    private static final Long CUSTOMER_ONE_ID = 1L;
    private static final Long CUSTOMER_TWO_ID = 2L;


    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void testListCustomers() throws Exception
    {
        CustomerDTO customerOne = new CustomerDTO();
        customerOne.setId(CUSTOMER_ONE_ID);
        customerOne.setFirstName(CUSTOMER_ONE_FIRST_NAME);
        customerOne.setLastName(CUSTOMER_ONE_LAST_NAME);

        CustomerDTO customerTwo = new CustomerDTO();
        customerTwo.setId(CUSTOMER_TWO_ID);
        customerTwo.setFirstName(CUSTOMER_TWO_FIRST_NAME);
        customerTwo.setLastName(CUSTOMER_TWO_LAST_NAME);

        List<CustomerDTO> customers = Arrays.asList(customerOne, customerTwo);

        when(customerService.getAllCustomers()).thenReturn(customers);


        mockMvc.perform(get(CustomerController.ENDPOINT_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));

    }

    @Test
    public void testGetByCustomerId() throws Exception
    {
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName(CUSTOMER_ONE_FIRST_NAME);
        customer.setLastName(CUSTOMER_ONE_LAST_NAME);
        customer.setId(CUSTOMER_ONE_ID);

        when(customerService.getCustomerById(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/api/v1/customers/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo(CUSTOMER_ONE_FIRST_NAME)));
    }
}

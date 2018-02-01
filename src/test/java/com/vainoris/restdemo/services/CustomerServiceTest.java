package com.vainoris.restdemo.services;

import com.vainoris.restdemo.api.v1.mapper.CustomerMapper;
import com.vainoris.restdemo.api.v1.model.CustomerDTO;
import com.vainoris.restdemo.domain.Customer;
import com.vainoris.restdemo.repositories.CustomerRepository;
import com.vainoris.restdemo.services.impl.CustomerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerServiceTest
{
    private static final String CUSTOMER_FIRST_NAME = "John";
    private static final String CUSTOMER_LAST_NAME = "Doe";
    private static final Long CUSTOMER_ID  = 1L;


    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);

        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception
    {
        //given
        List<Customer> customers = Arrays.asList(new Customer(), new Customer(), new Customer());

        //when
        when(customerRepository.findAll()).thenReturn(customers);

        List<CustomerDTO> customerDTOs = customerService.getAllCustomers();

        //then
        assertEquals(3, customerDTOs.size());
    }

    @Test
    public void getCustomerById() throws Exception
    {
        //given
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);
        customer.setFirstName(CUSTOMER_FIRST_NAME);
        customer.setLastName(CUSTOMER_LAST_NAME);

        Optional<Customer> customerOptional = Optional.of(customer);


        //when
        when(customerRepository.findById(anyLong())).thenReturn(customerOptional);
        CustomerDTO customerDTO = customerService.getCustomerById(CUSTOMER_ID);

        //then
        assertEquals(CUSTOMER_ID, customerDTO.getId());
        assertEquals(CUSTOMER_FIRST_NAME, customerDTO.getFirstName());
        assertEquals(CUSTOMER_LAST_NAME, customerDTO.getLastName());
        assertEquals("/shop/customers/" + CUSTOMER_ID, customerDTO.getCustomerUrl());
    }
}

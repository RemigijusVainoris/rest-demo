package com.vainoris.restdemo.api.v1.mapper;

import com.vainoris.restdemo.api.v1.model.CustomerDTO;
import com.vainoris.restdemo.domain.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerMapperTest
{
    private static final String CUSTOMER_FIRST_NAME = "John";
    private static final String CUSTOMER_LAST_NAME = "Doe";
    private static final Long CUSTOMER_ID  = 1L;

    private CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerDTO() throws Exception
    {
        Customer customer = new Customer();
        customer.setId(CUSTOMER_ID);
        customer.setFirstName(CUSTOMER_FIRST_NAME);
        customer.setLastName(CUSTOMER_LAST_NAME);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(CUSTOMER_ID, customerDTO.getId());
        assertEquals(CUSTOMER_FIRST_NAME, customerDTO.getFirstName());
        assertEquals(CUSTOMER_LAST_NAME, customerDTO.getLastName());
    }
}

package com.vainoris.restdemo.services;

import com.vainoris.restdemo.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService
{
    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);
}

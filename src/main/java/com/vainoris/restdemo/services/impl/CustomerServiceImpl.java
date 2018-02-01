package com.vainoris.restdemo.services.impl;

import com.vainoris.restdemo.api.v1.mapper.CustomerMapper;
import com.vainoris.restdemo.api.v1.model.CustomerDTO;
import com.vainoris.restdemo.domain.Customer;
import com.vainoris.restdemo.exceptions.ResourceNotFoundException;
import com.vainoris.restdemo.repositories.CustomerRepository;
import com.vainoris.restdemo.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService
{
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerMapper customerMapper, CustomerRepository customerRepository)
    {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getAllCustomers()
    {
        List<CustomerDTO> customers = new ArrayList<>();

        customerRepository.findAll()
                .iterator()
                .forEachRemaining(customer -> customers.add(customerMapper.customerToCustomerDTO(customer)));

        return customers;
    }

    @Override
    public CustomerDTO getCustomerById(Long id)
    {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        if (!customerOptional.isPresent())
            throw new ResourceNotFoundException("Customer with id = " + id + " was not found!");

        return customerMapper.customerToCustomerDTO(customerOptional.get());
    }
}

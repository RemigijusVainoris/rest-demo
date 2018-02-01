package com.vainoris.restdemo.controllers.v1;

import com.vainoris.restdemo.api.v1.model.CustomerDTO;
import com.vainoris.restdemo.api.v1.model.CustomerListDTO;
import com.vainoris.restdemo.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(CustomerController.ENDPOINT_URL)
public class CustomerController
{
    public static final String ENDPOINT_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService)
    {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers()
    {
        return new ResponseEntity<>(
                new CustomerListDTO(customerService.getAllCustomers()), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable("id") Long id)
    {
        return new ResponseEntity<>(customerService.getCustomerById(id), HttpStatus.OK);
    }
}

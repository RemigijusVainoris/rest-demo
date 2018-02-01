package com.vainoris.restdemo.repositories;

import com.vainoris.restdemo.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long>
{
}

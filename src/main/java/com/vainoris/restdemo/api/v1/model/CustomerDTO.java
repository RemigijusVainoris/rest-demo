package com.vainoris.restdemo.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerDTO
{
    private Long id;
    private String firstName;
    private String lastName;

    @JsonProperty("customer_url")
    public String getCustomerUrl()
    {
        return "/shop/customers/" + this.id;
    }
}

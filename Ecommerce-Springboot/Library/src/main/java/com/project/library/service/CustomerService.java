package com.project.library.service;

import com.project.library.dto.CustomerDto;
import com.project.library.model.Customer;

public interface CustomerService {
    Customer save(CustomerDto customerDto);
    Customer findByUsername(String username);
    CustomerDto getCustomer(String username);
    Customer changePass(CustomerDto customerDto);
}

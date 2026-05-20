package com.st.ecommerce.ecommerce_mongo.service;

import com.st.ecommerce.ecommerce_mongo.documents.Customer;
import com.st.ecommerce.ecommerce_mongo.dto.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerDto customerDto);

    CustomerDto findById(String id);

    List<CustomerDto> findAll();

    CustomerDto update(String id, CustomerDto customerDto);

    void delete(String id);
}

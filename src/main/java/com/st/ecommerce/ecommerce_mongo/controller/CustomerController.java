package com.st.ecommerce.ecommerce_mongo.controller;

import com.st.ecommerce.ecommerce_mongo.dto.CustomerDto;
import com.st.ecommerce.ecommerce_mongo.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerDto save(
            @RequestBody CustomerDto customerDto) {

        return customerService.save(customerDto);
    }

    @GetMapping("/{id}")
    public CustomerDto findById(
            @PathVariable String id) {

        return customerService.findById(id);
    }

    @GetMapping
    public List<CustomerDto> findAll() {

        return customerService.findAll();
    }

    @PutMapping("/{id}")
    public CustomerDto update(
            @PathVariable String id,
            @RequestBody CustomerDto customerDto) {

        return customerService.update(id,customerDto);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable String id) {

        customerService.delete(id);
    }
}

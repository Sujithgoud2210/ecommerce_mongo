package com.st.ecommerce.ecommerce_mongo.service;

import com.st.ecommerce.ecommerce_mongo.documents.Customer;
import com.st.ecommerce.ecommerce_mongo.dto.CustomerDto;
import com.st.ecommerce.ecommerce_mongo.exception.CustomerNotFoundException;
import com.st.ecommerce.ecommerce_mongo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    @Override
    public CustomerDto save(CustomerDto customerDto) {

        Customer customer = modelMapper.map(customerDto,Customer.class);

        return modelMapper.map(customerRepository.save(customer),
                CustomerDto.class
        );
    }


    @Override
    public CustomerDto findById(String id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer Not Found"));

        return modelMapper.map(customer,
                CustomerDto.class);
    }

    @Override
    public List<CustomerDto>findAll() {

        return customerRepository.findAll()
                .stream()
                .map(customer ->
                        modelMapper.map(customer,
                                CustomerDto.class))
                .toList();
    }


    @Override
    public CustomerDto update(String id,
                              CustomerDto customerDto) {

        Customer existingCustomer=
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer Not Found"));

        existingCustomer.setName(customerDto.getName());
        existingCustomer.setEmail(customerDto.getEmail());
        existingCustomer.setPhone(customerDto.getPhone());

        existingCustomer.setAddresses(
                modelMapper.map(
                        customerDto.getAddresses(),
                        List.class)
        );

        return modelMapper.map(
                customerRepository.save(existingCustomer),
                CustomerDto.class
        );
    }

    @Override
    public void delete(String id) {

        customerRepository.deleteById(id);
    }
}

package com.st.ecommerce.ecommerce_mongo.service;

import com.st.ecommerce.ecommerce_mongo.documents.Customer;
import com.st.ecommerce.ecommerce_mongo.dto.requestDto.LoginRequestDto;
import com.st.ecommerce.ecommerce_mongo.dto.requestDto.SignupRequestDto;
import com.st.ecommerce.ecommerce_mongo.dto.responseDto.CustomerDto;
import com.st.ecommerce.ecommerce_mongo.exception.CustomerExistsException;
import com.st.ecommerce.ecommerce_mongo.exception.CustomerNotFoundException;
import com.st.ecommerce.ecommerce_mongo.exception.InvalidCredentialsException;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    CustomerDto save(SignupRequestDto signupRequest) throws CustomerExistsException;
    CustomerDto login(LoginRequestDto loginRequest) throws CustomerNotFoundException, InvalidCredentialsException;
    List<CustomerDto> getAll();
    CustomerDto getById(String id) throws CustomerNotFoundException;
    CustomerDto update(String id, Customer Customer) throws CustomerNotFoundException;
    void delete(String id) throws CustomerNotFoundException;
}

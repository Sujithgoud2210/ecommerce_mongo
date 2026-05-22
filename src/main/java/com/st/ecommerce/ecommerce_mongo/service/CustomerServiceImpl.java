package com.st.ecommerce.ecommerce_mongo.service;

import com.st.ecommerce.ecommerce_mongo.documents.Customer;
import com.st.ecommerce.ecommerce_mongo.dto.requestDto.LoginRequestDto;
import com.st.ecommerce.ecommerce_mongo.dto.requestDto.SignupRequestDto;
import com.st.ecommerce.ecommerce_mongo.dto.responseDto.CustomerDto;
import com.st.ecommerce.ecommerce_mongo.enums.CustomerStatus;
import com.st.ecommerce.ecommerce_mongo.exception.CustomerExistsException;
import com.st.ecommerce.ecommerce_mongo.exception.CustomerNotFoundException;
import com.st.ecommerce.ecommerce_mongo.exception.InvalidCredentialsException;
import com.st.ecommerce.ecommerce_mongo.repository.AddressRepository;
import com.st.ecommerce.ecommerce_mongo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;


    @Override
    public CustomerDto save(SignupRequestDto signupRequestDto) throws CustomerExistsException {
        //1. validate if customer exists
        log.info("{} signuprequest {}",getClass().getSimpleName(),signupRequestDto);

        customerRepository.findByEmail(signupRequestDto.getEmail()).ifPresent(customer -> {
            throw new CustomerExistsException("Customer already exists with this email: " + signupRequestDto.getEmail() );
        });
        //2. Convert SignupRequest to Customer (Entity)
        /*Customer customer = new Customer();
        customer.setName(signupRequest.getName());
        customer.setEmail(signupRequest.getEmail());
        customer.setPassword(signupRequest.getPassword());
        customer.setPhone(signupRequest.getPhone());
        customer.setGender(signupRequest.getGender());*/
        Customer customer = modelMapper.map(signupRequestDto, Customer.class);
        //3. Save Customer
        customer.setCreatedDate(LocalDateTime.now());
        log.info("Saving customer {}",customer);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Saved customer {}",savedCustomer);
        //4. Convert Customer to CustomerDto
        // CustomerDto customerDto = CustomerDto.builder().id(savedCustomer.getId()).name(savedCustomer.getName()).email(savedCustomer.getEmail()).build();
        CustomerDto customerDto = modelMapper.map(savedCustomer, CustomerDto.class);
        log.info("CustomerDto {}",customerDto);
        return customerDto;
    }



    @Override
    public CustomerDto login(LoginRequestDto loginRequest) throws CustomerNotFoundException, InvalidCredentialsException {
        Customer customer =
                customerRepository
                        .findByEmail(
                                loginRequest.getEmail()
                        )
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer Not Found with this email :" + loginRequest.getEmail()
                                )
                        );

        if(!customer.getPassword()
                .equals(loginRequest.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid Password"
            );
        }

        return modelMapper.map(
                customer,
                CustomerDto.class
        );
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository
                .findAll()
                .stream()
                .map(customer ->
                        modelMapper.map(
                                customer,
                                CustomerDto.class
                        )
                )
                .toList();
    }

    @Override
    public CustomerDto getById(String id) throws CustomerNotFoundException {
        Customer customer =
                customerRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer Not Found with this id : " + id
                                )
                        );

        return modelMapper.map(
                customer,
                CustomerDto.class
        );
    }

    @Override
    public CustomerDto update(String id, Customer Customer) throws CustomerNotFoundException {
        Customer existingCustomer =
                customerRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer Not Found with id :" + id
                                )
                        );

        existingCustomer.setName(
                Customer.getName()
        );

        existingCustomer.setEmail(
                Customer.getEmail()
        );

        existingCustomer.setPhone(
                Customer.getPhone()
        );

        existingCustomer.setAddresses(
                modelMapper.map(
                        Customer.getAddresses(),
                        List.class
                )
        );

        Customer updatedCustomer =
                customerRepository
                        .save(existingCustomer);

        return modelMapper.map(
                updatedCustomer,
                CustomerDto.class
        );
    }

    @Override
    public void delete(String id) throws CustomerNotFoundException {
        Customer customer =
                customerRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new CustomerNotFoundException(
                                        "Customer Not Found"
                                )
                        );

        customerRepository.delete(customer);
    }

}
package com.st.ecommerce.ecommerce_mongo.controller;

import com.st.ecommerce.ecommerce_mongo.documents.Customer;
import com.st.ecommerce.ecommerce_mongo.dto.requestDto.LoginRequestDto;
import com.st.ecommerce.ecommerce_mongo.dto.requestDto.SignupRequestDto;
import com.st.ecommerce.ecommerce_mongo.dto.responseDto.CustomerDto;
import com.st.ecommerce.ecommerce_mongo.exception.CustomerNotFoundException;
import com.st.ecommerce.ecommerce_mongo.exception.InvalidCredentialsException;
import com.st.ecommerce.ecommerce_mongo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/customers")
@RequiredArgsConstructor

public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDto> save(@RequestBody SignupRequestDto signupRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.save(signupRequestDto));
    }



    // LOGIN CUSTOMER
    @PostMapping("/login")
    public ResponseEntity<CustomerDto> login(
            @RequestBody LoginRequestDto loginRequest)
            throws CustomerNotFoundException,
            InvalidCredentialsException {

        return ResponseEntity.ok(
                customerService.login(loginRequest)
        );
    }

    // GET ALL CUSTOMERS
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {

        return ResponseEntity.ok(
                customerService.getAll()
        );
    }

    // GET CUSTOMER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(
            @PathVariable String id)
            throws CustomerNotFoundException {

        return ResponseEntity.ok(
                customerService.getById(id)
        );
    }

    // UPDATE CUSTOMER
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(
            @PathVariable String id,
            @RequestBody Customer Customer)
            throws CustomerNotFoundException {

        return ResponseEntity.ok(
                customerService.update(id,
                        Customer)
        );
    }

    // DELETE CUSTOMER
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id)
            throws CustomerNotFoundException {

        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }


}

package com.st.ecommerce.ecommerce_mongo.repository;

import com.st.ecommerce.ecommerce_mongo.documents.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerRepository extends MongoRepository<Customer,String> {

    Optional<Customer> findByEmail(String email);


}

package com.st.ecommerce.ecommerce_mongo.repository;

import com.st.ecommerce.ecommerce_mongo.documents.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer,String> {



}

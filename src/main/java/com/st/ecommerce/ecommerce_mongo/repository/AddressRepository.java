package com.st.ecommerce.ecommerce_mongo.repository;

import com.st.ecommerce.ecommerce_mongo.documents.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address,String> {

}

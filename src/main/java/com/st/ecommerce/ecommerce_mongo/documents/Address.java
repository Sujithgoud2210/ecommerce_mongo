package com.st.ecommerce.ecommerce_mongo.documents;

import lombok.Data;

@Data
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipCode;
}

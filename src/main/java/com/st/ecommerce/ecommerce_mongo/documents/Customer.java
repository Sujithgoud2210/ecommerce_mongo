package com.st.ecommerce.ecommerce_mongo.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;

    private String name;
    private String email;
    private String phone;

    private List<Address> addresses;
}

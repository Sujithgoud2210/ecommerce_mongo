package com.st.ecommerce.ecommerce_mongo.documents;

import com.st.ecommerce.ecommerce_mongo.enums.CustomerStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String phone;
    private CustomerStatus customerStatus;
    private LocalDateTime createdDate;

    private List<Address> addresses;
}

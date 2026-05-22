package com.st.ecommerce.ecommerce_mongo.dto.requestDto;

import com.st.ecommerce.ecommerce_mongo.documents.Address;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;

import java.util.List;

@Data
@NoArgsConstructor
public class SignupRequestDto {
    private String name;
    private String email;
    private String password;
    private String phone;

    private List<Address> addresses;
}

package com.evaluation.mpesa.customers;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Customer {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Gender gender;
    private Double amount;
    private LocalDateTime created;

    public Customer(String firstName,
                    String lastName,
                    String email,
                    String phone,
                    Gender gender,
                    Double amount,
                    LocalDateTime created) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.amount = amount;
        this.created = created;
    }
}

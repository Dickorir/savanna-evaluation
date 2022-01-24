package com.evaluation.mpesa.transactions;

import com.evaluation.mpesa.customers.Gender;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Transaction {

    @Id
    private String id;

    private String receiver_phone;
    private String sender_phone;
    private Double amount;
    private LocalDateTime created;

    public Transaction() {
    }

    public Transaction(String receiver_phone,
                       String sender_phone,
                       Double amount,
                       LocalDateTime created) {
        this.receiver_phone = receiver_phone;
        this.sender_phone = sender_phone;
        this.amount = amount;
        this.created = created;
    }

}

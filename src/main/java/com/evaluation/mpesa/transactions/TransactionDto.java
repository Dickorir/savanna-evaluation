package com.evaluation.mpesa.transactions;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class TransactionDto {

    private String id;

    private String receiver_phone;
    private String sender_phone;
    private Double amount;

    public TransactionDto() {
    }

    public TransactionDto(String receiver_phone,
                       String sender_phone,
                       Double amount) {
        this.receiver_phone = receiver_phone;
        this.sender_phone = sender_phone;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "id='" + id + '\'' +
                ", receiver_phone='" + receiver_phone + '\'' +
                ", sender_phone='" + sender_phone + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReceiver_phone() {
        return receiver_phone;
    }

    public void setReceiver_phone(String receiver_phone) {
        this.receiver_phone = receiver_phone;
    }

    public String getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(String sender_phone) {
        this.sender_phone = sender_phone;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}

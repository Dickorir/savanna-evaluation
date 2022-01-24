package com.evaluation.mpesa.customers;

import com.evaluation.mpesa.model.ApiResponse;
import com.evaluation.mpesa.transactions.Transaction;
import com.evaluation.mpesa.transactions.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    private final TransactionService transactionService;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public ApiResponse addNewCustomer(Customer customer) {
        /* check if email and phone exist */
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmail(customer.getEmail());
        if (customerOptional.isPresent()) {
            //throw new IllegalStateException(customer.getEmail() + " email taken");
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setCode(201);
            apiResponse.setMessage("email is taken");

            return apiResponse;
        }
        Optional<Customer> customerPhone = customerRepository.findCustomerByPhone(customer.getPhone());
        if (customerPhone.isPresent()) {
//            throw new IllegalStateException(customer.getPhone() + " phone number taken");

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setCode(201);
            apiResponse.setMessage(customer.getPhone() + " phone number taken");

            return apiResponse;
        }

        customerRepository.save(customer);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(201);
        apiResponse.setMessage("Customer Created Successfully");

        return apiResponse;
    }

    public ApiResponse transferMoney(String receiver_phone, String sender_phone, Double amountToSend) {

        String response;
        Optional<Customer> the_sender = customerRepository.findCustomerByPhone(sender_phone);
        if (the_sender.isPresent()) {
            Customer sender = the_sender.get();

            if (amountToSend <= sender.getAmount()) {
                return handleReceiverAndSendMoney(sender, amountToSend, receiver_phone);
            } else {
                response = "Insufficient funds";
            }
        } else {
            response = "Sender " + sender_phone + " cannot be found";
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(400);
        apiResponse.setMessage(response);

        return apiResponse;

    }

    public ApiResponse handleReceiverAndSendMoney(Customer sender, Double amountToSend, String receiver_phone) {
        Optional<Customer> the_receiver = customerRepository.findCustomerByPhone(receiver_phone);
        if (the_receiver.isPresent()) {
            Customer receiver = the_receiver.get();
            System.out.println(receiver);
            System.out.println("found 2");

            // reduce amount of sender
            // add amount of receiver
            // store this to transaction document

            Double receiverFinalAmount = amountToSend + receiver.getAmount();
            Double sendersFinalAmount = sender.getAmount() - amountToSend;

            receiver.setAmount(receiverFinalAmount);
            sender.setAmount(sendersFinalAmount);

            customerRepository.save(receiver);
            customerRepository.save(sender);

            Transaction transaction = new Transaction();
            transaction.setSender_phone(sender.getPhone());
            transaction.setReceiver_phone(receiver_phone);
            transaction.setAmount(amountToSend);
            transaction.setCreated(LocalDateTime.now());

            transactionService.addNewTransaction(transaction);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setCode(200);
            apiResponse.setMessage("Money Send Successfully");

            return apiResponse;

        } else {
            throw new IllegalStateException("Receiver " + receiver_phone + " cannot be found");
        }
    }
}

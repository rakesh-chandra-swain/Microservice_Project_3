package com.bluepal.service;



import java.util.List;

import com.bluepal.model.Order;
import com.bluepal.model.Seller;
import com.bluepal.model.Transaction;

public interface TransactionService {

    Transaction createTransaction(Order order);
    List<Transaction> getTransactionBySeller(Seller seller);
    List<Transaction>getAllTransactions();
}

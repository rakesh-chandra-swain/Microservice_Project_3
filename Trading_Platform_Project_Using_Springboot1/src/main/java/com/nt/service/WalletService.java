package com.nt.service;

import com.nt.model.Order;
import com.nt.model.User;
import com.nt.model.Wallet;



public interface WalletService {
	Wallet getUserWallet(User user);
	Wallet addBalance(Wallet wallet,Long money);
	Wallet findWalletById(Long id) throws Exception;
	Wallet wallteToWalletTransfer(User sender,Wallet receiverWallet,Long amount) throws Exception;
	Wallet payOrderPayment(Order order,User user) throws Exception;
}

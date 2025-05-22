package com.nt.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.domain.OrderType;
import com.nt.model.Order;
import com.nt.model.User;
import com.nt.model.Wallet;
import com.nt.respository.WalletRepository;
@Service
public class WalletServiceImpl implements WalletService {
	
	@Autowired
	private WalletRepository walletRepository;

	@Override
	public Wallet getUserWallet(User user) {
		Wallet wallet=walletRepository.findByUserId(user.getId());
		if(wallet==null) {
			wallet=new Wallet();
			wallet.setUser(user);
		}
				return wallet;
	}

	@Override
	public Wallet addBalance(Wallet wallet, Long money) {
		BigDecimal balance=wallet.getBalance();
		BigDecimal newBalance=balance.add(BigDecimal.valueOf(money));
		
		wallet.setBalance(newBalance);
		return walletRepository.save(wallet);
	}

	@Override
	public Wallet findWalletById(Long id) throws Exception {
		Optional<Wallet> wallet=walletRepository.findById(id);
		if(wallet.isPresent()) {
			return wallet.get();
		}
		throw new Exception("wallet not found");
	}

	@Override
	public Wallet wallteToWalletTransfer(User sender, Wallet receiverWallet, Long amount) throws Exception {
		Wallet senderWallet=getUserWallet(sender);
		
		if(senderWallet.getBalance().compareTo(BigDecimal.valueOf(amount))<0) {
			throw new Exception("insufficient balance");
		}
		BigDecimal senderBalance=senderWallet
				.getBalance()
				.subtract(BigDecimal.valueOf(amount));
		
		senderWallet.setBalance(senderBalance);
		walletRepository.save(senderWallet);
		
		BigDecimal receverBalance=receiverWallet
				.getBalance()
				.add(BigDecimal.valueOf(amount));
		receiverWallet.setBalance(receverBalance);
		walletRepository.save(receiverWallet);
		
		return senderWallet;
	}

	@Override
	public Wallet payOrderPayment(Order order, User user) throws Exception {
		Wallet wallet=getUserWallet(user);
		
		if(order.getOrderType().equals(OrderType.BUY)) {
			BigDecimal newBalance=wallet.getBalance().subtract(order.getPrice());
			if(newBalance.compareTo(order.getPrice())<0) {
				throw new Exception("insufficent fund in this Trasanction");
			}
			wallet.setBalance(newBalance);
		}
		else {
			BigDecimal newBalance=wallet.getBalance().add(order.getPrice());
			wallet.setBalance(newBalance);
		}
		walletRepository.save(wallet);
		return wallet;
	}

}

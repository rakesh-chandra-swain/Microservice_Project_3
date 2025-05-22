package com.nt.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.model.Coin;

public interface CoinRepository extends JpaRepository<Coin, String> {

}

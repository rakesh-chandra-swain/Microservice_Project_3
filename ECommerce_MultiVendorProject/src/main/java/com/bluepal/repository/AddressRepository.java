package com.bluepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bluepal.model.Address;



public interface AddressRepository extends JpaRepository<Address, Long> {

}

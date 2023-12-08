package com.hideaway.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hideaway.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}

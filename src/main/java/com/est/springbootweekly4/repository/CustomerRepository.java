package com.est.springbootweekly4.repository;

import com.est.springbootweekly4.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

package com.est.springbootweekly4.repository;

import com.est.springbootweekly4.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

}

package com.est.springbootweekly4.repository;

import com.est.springbootweekly4.domain.Order;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByStoreIdAndOrderDateBetween(Long storeId, LocalDateTime startDate, LocalDateTime endDate);
}

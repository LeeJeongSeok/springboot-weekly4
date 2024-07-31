package com.est.springbootweekly4.repository;

import com.est.springbootweekly4.domain.OrderItem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	List<OrderItem> findTop3ByOrderByQuantityDesc();
}

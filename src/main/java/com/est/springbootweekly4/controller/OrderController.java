package com.est.springbootweekly4.controller;

import com.est.springbootweekly4.dto.OrderDto;
import com.est.springbootweekly4.service.OrderService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/store/{storeId}")
	public ResponseEntity<List<OrderDto>> getOrdersByStoreAndDate(@PathVariable Long storeId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		return ResponseEntity.ok(orderService.getOrdersByStoreAndDate(storeId, startDate, endDate));
	}

	@GetMapping("/sales/store/{storeId}")
	public ResponseEntity<Double> getSalesByStoreAndDate(@PathVariable Long storeId, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
		return ResponseEntity.ok(orderService.getSalesByStoreAndDate(storeId, startDate, endDate));
	}

	@PostMapping
	public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
		return ResponseEntity.ok(orderService.createOrder(orderDto));
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
		return ResponseEntity.ok(orderService.updateOrderStatus(id, status));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelOrder(@PathVariable Long id) {
		orderService.cancelOrder(id);
		return ResponseEntity.noContent().build();
	}
}

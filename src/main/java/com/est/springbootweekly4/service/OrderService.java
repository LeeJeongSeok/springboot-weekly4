package com.est.springbootweekly4.service;

import com.est.springbootweekly4.domain.Customer;
import com.est.springbootweekly4.domain.Menu;
import com.est.springbootweekly4.domain.Order;
import com.est.springbootweekly4.domain.Store;
import com.est.springbootweekly4.dto.OrderDto;
import com.est.springbootweekly4.dto.OrderItemDto;
import com.est.springbootweekly4.repository.CustomerRepository;
import com.est.springbootweekly4.repository.MenuRepository;
import com.est.springbootweekly4.repository.OrderItemRepository;
import com.est.springbootweekly4.repository.OrderRepository;
import com.est.springbootweekly4.repository.StoreRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final MenuRepository menuRepository;
	private final CustomerRepository customerRepository;
	private final StoreRepository storeRepository;

	@Transactional
	public OrderDto createOrder(OrderDto orderDto) {
		Customer customer = customerRepository.findById(orderDto.getCustomerId())
			.orElseThrow(() -> new IllegalArgumentException("고객을 찾을 수 없습니다."));
		Store store = storeRepository.findById(orderDto.getStoreId())
			.orElseThrow(() -> new IllegalArgumentException("매장을 찾을 수 없습니다."));
		List<Menu> menus = menuRepository.findAllById(
			orderDto.getOrderItems().stream().map(OrderItemDto::getMenuId).collect(Collectors.toList()));

		Order order = orderDto.toEntity(customer, store, menus);

		double totalAmount = calculateTotalAmount(orderDto, menus);
		order.setTotalAmount(totalAmount);

		for (OrderItemDto orderItemDto : orderDto.getOrderItems()) {
			Menu menu = menus.stream()
				.filter(m -> m.getId().equals(orderItemDto.getMenuId()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
			order.addOrderItem(orderItemDto.toEntity(order, menu));
		}

		Order savedOrder = orderRepository.save(order);
		return OrderDto.fromEntity(savedOrder);
	}

	@Transactional
	public OrderDto updateOrderStatus(Long orderId, String status) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
		order.setStatus(status);
		Order updatedOrder = orderRepository.save(order);
		return OrderDto.fromEntity(updatedOrder);
	}

	@Transactional
	public void cancelOrder(Long orderId) {
		Order order = orderRepository.findById(orderId)
			.orElseThrow(() -> new IllegalArgumentException("주문을 찾을 수 없습니다."));
		order.setStatus("CANCELLED");
		orderRepository.save(order);
	}

	public List<OrderDto> getOrdersByStoreAndDate(Long storeId, LocalDateTime startDate, LocalDateTime endDate) {
		List<Order> orders = orderRepository.findByStoreIdAndOrderDateBetween(storeId, startDate, endDate);
		return orders.stream().map(OrderDto::fromEntity).collect(Collectors.toList());
	}

	public double getSalesByStoreAndDate(Long storeId, LocalDateTime startDate, LocalDateTime endDate) {
		List<Order> orders = orderRepository.findByStoreIdAndOrderDateBetween(storeId, startDate, endDate);
		return orders.stream().mapToDouble(Order::getTotalAmount).sum();
	}

	private double calculateTotalAmount(OrderDto orderDto, List<Menu> menus) {
		return orderDto.getOrderItems().stream()
			.mapToDouble(orderItemDto -> {
				Menu menu = menus.stream()
					.filter(m -> m.getId().equals(orderItemDto.getMenuId()))
					.findFirst()
					.orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
				return menu.getPrice() * orderItemDto.getQuantity();
			})
			.sum();
	}
}


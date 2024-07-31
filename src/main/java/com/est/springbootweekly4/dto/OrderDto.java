package com.est.springbootweekly4.dto;

import com.est.springbootweekly4.domain.Customer;
import com.est.springbootweekly4.domain.Menu;
import com.est.springbootweekly4.domain.Order;
import com.est.springbootweekly4.domain.OrderItem;
import com.est.springbootweekly4.domain.Store;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {
	private Long id;
	private Long customerId;
	private Long storeId;
	private List<OrderItemDto> orderItems;
	private String status;
	private LocalDateTime orderDate;
	private Double totalAmount;

	public static OrderDto fromEntity(Order order) {
		return OrderDto.builder()
			.id(order.getId())
			.customerId(order.getCustomer().getId())
			.storeId(order.getStore().getId())
			.status(order.getStatus())
			.orderDate(order.getOrderDate())
			.totalAmount(order.getTotalAmount())
			.orderItems(order.getOrderItems().stream().map(OrderItemDto::fromEntity).collect(Collectors.toList()))
			.build();
	}

	public Order toEntity(Customer customer, Store store, List<Menu> menus) {
		Order order = Order.builder()
			.id(this.id)
			.customer(customer)
			.store(store)
			.status(this.status)
			.orderDate(this.orderDate != null ? this.orderDate : LocalDateTime.now())
			.totalAmount(this.totalAmount)
			.build();

		List<OrderItem> orderItems = this.orderItems.stream().map(itemDto -> {
			Menu menu = menus.stream()
				.filter(m -> m.getId().equals(itemDto.getMenuId()))
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
			OrderItem orderItem = itemDto.toEntity(order, menu);
			order.addOrderItem(orderItem);
			return orderItem;
		}).collect(Collectors.toList());

		order.setOrderItems(orderItems);
		return order;
	}
}

package com.est.springbootweekly4.dto;

import com.est.springbootweekly4.domain.Menu;
import com.est.springbootweekly4.domain.Order;
import com.est.springbootweekly4.domain.OrderItem;
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
public class OrderItemDto {
	private Long id;
	private Long menuId;
	private Integer quantity;
	private Double price;

	public static OrderItemDto fromEntity(OrderItem orderItem) {
		return OrderItemDto.builder()
			.id(orderItem.getId())
			.menuId(orderItem.getMenu().getId())
			.quantity(orderItem.getQuantity())
			.price(orderItem.getPrice())
			.build();
	}

	public OrderItem toEntity(Order order, Menu menu) {
		return OrderItem.builder()
			.id(this.id)
			.order(order)
			.menu(menu)
			.quantity(this.quantity)
			.price(this.price)
			.build();
	}
}

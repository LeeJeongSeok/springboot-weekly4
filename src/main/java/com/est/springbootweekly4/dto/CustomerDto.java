package com.est.springbootweekly4.dto;

import com.est.springbootweekly4.domain.Customer;
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
public class CustomerDto {
	private Long id;
	private String name;
	private String phone;
	private String address;

	public static CustomerDto fromEntity(Customer customer) {
		return CustomerDto.builder()
			.id(customer.getId())
			.name(customer.getName())
			.phone(customer.getPhone())
			.address(customer.getAddress())
			.build();
	}

	public Customer toEntity() {
		return Customer.builder()
			.id(this.id)
			.name(this.name)
			.phone(this.phone)
			.address(this.address)
			.build();
	}
}

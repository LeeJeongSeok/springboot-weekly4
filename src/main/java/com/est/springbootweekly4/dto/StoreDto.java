package com.est.springbootweekly4.dto;

import com.est.springbootweekly4.domain.Store;
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
public class StoreDto {
	private Long id;
	private String name;
	private String address;
	private String phone;

	public static StoreDto fromEntity(Store store) {
		return StoreDto.builder()
			.id(store.getId())
			.name(store.getName())
			.address(store.getAddress())
			.phone(store.getPhone())
			.build();
	}

	public Store toEntity() {
		return Store.builder()
			.id(this.id)
			.name(this.name)
			.address(this.address)
			.phone(this.phone)
			.build();
	}
}
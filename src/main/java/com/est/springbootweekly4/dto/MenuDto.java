package com.est.springbootweekly4.dto;

import com.est.springbootweekly4.domain.Menu;
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
public class MenuDto {
	private Long id;
	private String name;
	private String category;
	private Double price;
	private String description;

	public static MenuDto fromEntity(Menu menu) {
		return MenuDto.builder()
			.id(menu.getId())
			.name(menu.getName())
			.category(menu.getCategory())
			.price(menu.getPrice())
			.description(menu.getDescription())
			.build();
	}

	public Menu toEntity() {
		return Menu.builder()
			.id(this.id)
			.name(this.name)
			.category(this.category)
			.price(this.price)
			.description(this.description)
			.build();
	}
}

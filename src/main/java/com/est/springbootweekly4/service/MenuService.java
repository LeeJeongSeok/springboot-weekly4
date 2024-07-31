package com.est.springbootweekly4.service;

import com.est.springbootweekly4.domain.Menu;
import com.est.springbootweekly4.domain.OrderItem;
import com.est.springbootweekly4.dto.MenuDto;
import com.est.springbootweekly4.repository.MenuRepository;
import com.est.springbootweekly4.repository.OrderItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

	private final MenuRepository menuRepository;
	private final OrderItemRepository orderItemRepository;

	public MenuDto createMenu(MenuDto menuDto) {
		Menu menu = menuDto.toEntity();
		Menu savedMenu = menuRepository.save(menu);
		return MenuDto.fromEntity(savedMenu);
	}

	public MenuDto updateMenu(Long id, MenuDto menuDto) {
		Menu existingMenu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
		existingMenu.setName(menuDto.getName());
		existingMenu.setCategory(menuDto.getCategory());
		existingMenu.setPrice(menuDto.getPrice());
		existingMenu.setDescription(menuDto.getDescription());
		Menu updatedMenu = menuRepository.save(existingMenu);
		return MenuDto.fromEntity(updatedMenu);
	}

	public void deleteMenu(Long id) {
		menuRepository.deleteById(id);
	}

	public MenuDto getMenuById(Long id) {
		Menu menu = menuRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("메뉴를 찾을 수 없습니다."));
		return MenuDto.fromEntity(menu);
	}

	public List<MenuDto> getMenusByCategory(String category) {
		List<Menu> menus = menuRepository.findByCategory(category);
		return menus.stream().map(MenuDto::fromEntity).collect(Collectors.toList());
	}

	public List<MenuDto> getTop3PopularMenus() {
		List<Menu> menus = orderItemRepository.findTop3ByOrderByQuantityDesc()
			.stream()
			.map(OrderItem::getMenu)
			.collect(Collectors.toList());
		return menus.stream().map(MenuDto::fromEntity).collect(Collectors.toList());
	}
}

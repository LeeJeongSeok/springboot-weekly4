package com.est.springbootweekly4.controller;

import com.est.springbootweekly4.dto.MenuDto;
import com.est.springbootweekly4.service.MenuService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menus")
@RequiredArgsConstructor
public class MenuController {

	private final MenuService menuService;

	@GetMapping("/{id}")
	public ResponseEntity<MenuDto> getMenuById(@PathVariable Long id) {
		return ResponseEntity.ok(menuService.getMenuById(id));
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<MenuDto>> getMenusByCategory(@PathVariable String category) {
		return ResponseEntity.ok(menuService.getMenusByCategory(category));
	}

	@GetMapping("/popular")
	public ResponseEntity<List<MenuDto>> getTop3PopularMenus() {
		return ResponseEntity.ok(menuService.getTop3PopularMenus());
	}

	@PostMapping
	public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) {
		return ResponseEntity.ok(menuService.createMenu(menuDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<MenuDto> updateMenu(@PathVariable Long id, @RequestBody MenuDto menuDto) {
		return ResponseEntity.ok(menuService.updateMenu(id, menuDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMenu(@PathVariable Long id) {
		menuService.deleteMenu(id);
		return ResponseEntity.noContent().build();
	}
}

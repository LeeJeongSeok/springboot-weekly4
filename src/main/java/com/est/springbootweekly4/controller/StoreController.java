package com.est.springbootweekly4.controller;

import com.est.springbootweekly4.dto.StoreDto;
import com.est.springbootweekly4.service.StoreService;
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
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

	private final StoreService storeService;

	@GetMapping("/{id}")
	public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id) {
		return ResponseEntity.ok(storeService.getStoreById(id));
	}

	@PostMapping
	public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto) {
		return ResponseEntity.ok(storeService.createStore(storeDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<StoreDto> updateStore(@PathVariable Long id, @RequestBody StoreDto storeDto) {
		return ResponseEntity.ok(storeService.updateStore(id, storeDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStore(@PathVariable Long id) {
		storeService.deleteStore(id);
		return ResponseEntity.noContent().build();
	}
}

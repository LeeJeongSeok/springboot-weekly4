package com.est.springbootweekly4.service;

import com.est.springbootweekly4.domain.Store;
import com.est.springbootweekly4.dto.StoreDto;
import com.est.springbootweekly4.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {

	private final StoreRepository storeRepository;

	public StoreDto createStore(StoreDto storeDto) {
		Store store = storeDto.toEntity();
		Store savedStore = storeRepository.save(store);
		return StoreDto.fromEntity(savedStore);
	}

	public StoreDto updateStore(Long id, StoreDto storeDto) {
		Store existingStore = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("매장을 찾을 수 없습니다."));
		existingStore.setName(storeDto.getName());
		existingStore.setAddress(storeDto.getAddress());
		existingStore.setPhone(storeDto.getPhone());
		Store updatedStore = storeRepository.save(existingStore);
		return StoreDto.fromEntity(updatedStore);
	}

	public void deleteStore(Long id) {
		storeRepository.deleteById(id);
	}

	public StoreDto getStoreById(Long id) {
		Store store = storeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("매장을 찾을 수 없습니다."));
		return StoreDto.fromEntity(store);
	}
}

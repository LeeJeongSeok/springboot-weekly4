package com.est.springbootweekly4.service;

import com.est.springbootweekly4.domain.Customer;
import com.est.springbootweekly4.dto.CustomerDto;
import com.est.springbootweekly4.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository customerRepository;

	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = customerDto.toEntity();
		Customer savedCustomer = customerRepository.save(customer);
		return CustomerDto.fromEntity(savedCustomer);
	}

	public CustomerDto updateCustomer(Long id, CustomerDto customerDto) {
		Customer existingCustomer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("고객을 찾을 수 없습니다."));
		existingCustomer.setName(customerDto.getName());
		existingCustomer.setPhone(customerDto.getPhone());
		existingCustomer.setAddress(customerDto.getAddress());
		Customer updatedCustomer = customerRepository.save(existingCustomer);
		return CustomerDto.fromEntity(updatedCustomer);
	}

	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	public CustomerDto getCustomerById(Long id) {
		Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("고객을 찾을 수 없습니다."));
		return CustomerDto.fromEntity(customer);
	}
}

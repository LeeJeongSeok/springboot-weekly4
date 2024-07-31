package com.est.springbootweekly4.controller;

import com.est.springbootweekly4.dto.CustomerDto;
import com.est.springbootweekly4.service.CustomerService;
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
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService customerService;

	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) {
		return ResponseEntity.ok(customerService.getCustomerById(id));
	}

	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.createCustomer(customerDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDto) {
		return ResponseEntity.ok(customerService.updateCustomer(id, customerDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
		customerService.deleteCustomer(id);
		return ResponseEntity.noContent().build();
	}

}

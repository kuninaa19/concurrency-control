package com.event.fcfssystem.customer;

import com.event.fcfssystem.customer.dto.request.CreateCustomerRequestDto;
import com.event.fcfssystem.customer.dto.response.CreateCustomerResponseDto;
import com.event.fcfssystem.customer.dto.response.FindCustomerResponseDto;
import com.event.fcfssystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public FindCustomerResponseDto findOne(Long id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return FindCustomerResponseDto.of(customer);
    }

    public CreateCustomerResponseDto save(CreateCustomerRequestDto createCustomerDto) {
        Customer customer = this.customerRepository.save(createCustomerDto.toEntity());
        return CreateCustomerResponseDto.of(customer);
    }
}

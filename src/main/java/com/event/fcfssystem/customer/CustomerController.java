package com.event.fcfssystem.customer;

import com.event.fcfssystem.customer.dto.request.CreateCustomerRequestDto;
import com.event.fcfssystem.customer.dto.response.CreateCustomerResponseDto;
import com.event.fcfssystem.customer.dto.response.FindCustomerResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    public FindCustomerResponseDto findOne(@PathVariable Long id) {
        return this.customerService.findOne(id);
    }

    @PostMapping("")
    public CreateCustomerResponseDto save(@RequestBody CreateCustomerRequestDto createCustomerDto) {
        return this.customerService.save(createCustomerDto);
    }
}

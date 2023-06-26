package com.event.fcfssystem.customer.dto.response;

import com.event.fcfssystem.model.Customer;
import lombok.Getter;

@Getter
public class CreateCustomerResponseDto {
    private Long id;
    private String email;


    public static CreateCustomerResponseDto of(Customer customer) {
        CreateCustomerResponseDto createCustomerResponseDto = new CreateCustomerResponseDto();
        createCustomerResponseDto.id = customer.getId();
        createCustomerResponseDto.email = customer.getEmail();

        return createCustomerResponseDto;
    }
}

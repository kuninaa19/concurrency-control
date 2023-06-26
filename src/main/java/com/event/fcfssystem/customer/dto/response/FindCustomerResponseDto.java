package com.event.fcfssystem.customer.dto.response;

import com.event.fcfssystem.model.Customer;
import lombok.Getter;

@Getter
public class FindCustomerResponseDto {
    private Long id;
    private String email;

    public static FindCustomerResponseDto of(Customer customer) {
        FindCustomerResponseDto findCustomerResponseDto = new FindCustomerResponseDto();
        findCustomerResponseDto.id = customer.getId();
        findCustomerResponseDto.email = customer.getEmail();
        return findCustomerResponseDto;
    }
}

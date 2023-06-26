package com.event.fcfssystem.customer.dto.request;


import com.event.fcfssystem.model.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCustomerRequestDto {
    private String email;

    public CreateCustomerRequestDto(String email) {
        this.email = email;
    }

    public Customer toEntity() {
        return new Customer(getEmail());
    }
}

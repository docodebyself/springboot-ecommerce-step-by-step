package com.project.library.dto;

import com.project.library.model.City;
import com.project.library.model.Order;
import com.project.library.model.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @Size(min = 3, max = 10, message = "First name contains 3-10 characters")
    private String firstName;

    @Size(min = 3, max = 10, message = "Last name contains 3-10 characters")
    private String lastName;
    private String username;
    @Size(min = 3, max = 15, message = "Password contains 3-10 characters")
    private String password;

    @Size(min = 10, max = 15, message = "Phone number contains 3-10 characters")
    private String phoneNumber;

    @Size(min = 10, max = 50, message = "Invalid address")
    private String address;
    private String confirmPassword;
    private City city;
    private String image;
    private String country;

}

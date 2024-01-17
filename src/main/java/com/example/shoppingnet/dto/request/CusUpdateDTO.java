package com.example.shoppingnet.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CusUpdateDTO {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private double customerSalary;
}

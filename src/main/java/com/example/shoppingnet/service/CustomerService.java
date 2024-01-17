package com.example.shoppingnet.service;

import com.example.shoppingnet.dto.CustomerDTO;
import com.example.shoppingnet.dto.request.CusUpdateDTO;
import com.example.shoppingnet.entity.Customer;

import java.util.List;

public interface CustomerService {

   public String saveCustomer(CustomerDTO customerDTO);

   String updateCustomer(CusUpdateDTO cusUpdateDTO);

   List<CustomerDTO> getAllCustomers();

   String deleteCustomer();

   String deleteCustomer(int customerId);

   CustomerDTO getCustomerById(int customerId);

    List<CustomerDTO> getAllCustomersByActive(boolean activeStatus);
}

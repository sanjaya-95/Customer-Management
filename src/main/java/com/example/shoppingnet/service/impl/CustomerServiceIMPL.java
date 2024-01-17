package com.example.shoppingnet.service.impl;

import com.example.shoppingnet.dto.CustomerDTO;
import com.example.shoppingnet.dto.request.CusUpdateDTO;
import com.example.shoppingnet.entity.Customer;
import com.example.shoppingnet.repo.CustomerRepo;
import com.example.shoppingnet.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceIMPL implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
     /*   Customer customer = new Customer(
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerSalary(),
                customerDTO.getNic(),
                customerDTO.isActiveStatus()
        );
        customerRepo.save(customer);
        return "saved";*/

        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setActiveStatus(true);

        if (!customerRepo.existsById(customer.getCustomerId())){
            customerRepo.save(customer);
            return customer.getCustomerId()+ "saved successfully";
        }
        else{
            throw new DuplicateKeyException("Already Addded");
        }
    }

    public String updateCustomer(CusUpdateDTO cusUpdateDTO){

        if(customerRepo.existsById(cusUpdateDTO.getCustomerId())){
            customerRepo.save(modelMapper.map(cusUpdateDTO, Customer.class));
            return cusUpdateDTO.getCustomerName()+ " updated";
        } else{
            return "No customer found ";
        }
    }

    public List<CustomerDTO> getAllCustomers(){
       List<Customer> customerList = customerRepo.findAll();

//       return modelMapper.map(customerList, new TypeToken<ArrayList<CustomerDTO>>(){}.getType());

        List<CustomerDTO> list = modelMapper.map(customerList, new TypeToken<ArrayList<CustomerDTO>>(){}.getType());
        return list;
    }

    @Override
    public String deleteCustomer() {
        return null;
    }

    @Override
    public String deleteCustomer(int customerId) {
        if(customerRepo.existsById(customerId)){
            customerRepo.deleteById(customerId);
            return "Delete Successful "+customerId;
        } else {
            throw new RuntimeException("No customer found in this id");
        }
    }


    @Override
    public CustomerDTO getCustomerById(int customerId) {
        if(customerRepo.existsById(customerId)){
            Customer customer = customerRepo.getById(customerId);
            CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class );
            return customerDTO;
        }else  {
            throw new RuntimeException("No Customer found");
        }


    }

    @Override
    public List<CustomerDTO> getAllCustomersByActive(boolean activeStatus) {

        List<Customer> customerList = customerRepo.findAllByActiveStatusEquals(activeStatus);
        List<CustomerDTO> list1 = modelMapper.map(customerList, new TypeToken<ArrayList<CustomerDTO>>(){}.getType());
        return list1;

    }


}

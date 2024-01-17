package com.example.shoppingnet.controller;

import com.example.shoppingnet.dto.CustomerDTO;

import com.example.shoppingnet.dto.request.CusUpdateDTO;
import com.example.shoppingnet.service.CustomerService;
import com.example.shoppingnet.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@CrossOrigin


public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/save")
   /* public String saveCustomer(@RequestBody CustomerDTO customerDTO){
        String message = customerService.saveCustomer(customerDTO);
        return message;

    }*/

    public ResponseEntity<String> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        String savedCustomer = customerService.saveCustomer(customerDTO);
        return new ResponseEntity<String>(savedCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
   /* public String updateCustomer(@RequestBody CusUpdateDTO cusUpdateDTO){
        String updated = customerService.updateCustomer(cusUpdateDTO);
        return updated;
    }*/

    public ResponseEntity<StandardResponse> updateCustomer(@RequestBody CusUpdateDTO cusUpdateDTO) {
        String response = customerService.updateCustomer(cusUpdateDTO);


/*        ResponseEntity<StandardResponse> response = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "success", res), HttpStatus.CREATED
        );

        return response;*/

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "success", response),
                HttpStatus.CREATED
        );

    }

    @GetMapping("/getAllCustomers")

    public ResponseEntity<StandardResponse> getAllCustomers(){
        List<CustomerDTO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<StandardResponse> (
                new StandardResponse(200, "Success", allCustomers ),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "/getAllCustomers-By-active",
            params = "status"
    )

    public ResponseEntity<StandardResponse> getAllCustomersByActive(@RequestParam(value = "status") boolean activeStatus){
        List<CustomerDTO> allSctiveCustomers = customerService.getAllCustomersByActive(activeStatus);
        return new ResponseEntity<StandardResponse> (
                new StandardResponse(200, "Success", allSctiveCustomers ),
                HttpStatus.OK
        );
    }



    @DeleteMapping(
            path = "/delete",
            params = "id" )

    public ResponseEntity<StandardResponse> deleteCustomer(@RequestParam(value = "id") int customerId){
        String deleted = customerService.deleteCustomer();
        return new ResponseEntity<StandardResponse> (
                new StandardResponse(200, "Success", deleted ),
                HttpStatus.OK
        );
    }

    @GetMapping(
            path = "/get-by-id",
            params = "id" )

    public CustomerDTO getCustomerById(@RequestParam(value = "id") int customerId){
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        return customerDTO;
    }

   /* public getCustomerById(int ){

    }*/
}

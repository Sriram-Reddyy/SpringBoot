package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class CustomerService {
	@Autowired
	private Customer_JPA c;
	
	//can be used for mocking
	CustomerService(Customer_JPA c){
		this.c = c;
	}
	
	//Service class for getting all customers
	public List<Customer> getCustomers(){
		return c.findAll();
	}
	
	//Service class for getting specific customer
	public Customer getSpecificCustomer(int id){
		return c.getOne(id);
	}
	
	//Service class for posting
	public boolean postCustomer(Customer cust) {
		if(cust.getId()>0 && cust.getName()!=null && cust.getSsn()>0 && cust.getName()!="") {
			if(c.save(cust)!=null) {
				return true;
			}
		}
		return false;
	}
	
	//Service class for updating
	public boolean putCustomer(Customer cust) {
		if(cust.getId()>0 && cust.getName()!=null && cust.getSsn()>0 && cust.getName()!="") {
			if(c.save(cust)!=null) {
				return true;
			}
		}
		return false;
	}
	
	//Service class for deletion
	public boolean deleteCustomer(int id) {
		if(id>0) {
			Customer temp = c.getById(id);
			if(temp!=null) {
				c.delete(temp);
				return true;
			}
		}
		return false;
	}	
}

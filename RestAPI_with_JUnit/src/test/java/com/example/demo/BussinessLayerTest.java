package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class BussinessLayerTest {
	
	@Mock
	private Customer_JPA c_jpa;
	private CustomerService cs;
	private AutoCloseable ac;
	
	
	@BeforeEach
	void setUp() {
	    ac = MockitoAnnotations.openMocks(this);
	    cs = new CustomerService(c_jpa);//mocking up
	    
	    //for put and post test case
	    Customer expec = new Customer(12,"vikram",1213214);
		Customer res = new Customer(12,"vikram",1213214);
		when(c_jpa.save(expec)).thenReturn(res);
	 }
	@AfterEach
	void tearDown() {
		try {
			ac.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	 }
	
	
	//Testing for get operation
	@Test
	void getCustomers() {
		List<Customer> temp = new ArrayList();
		temp.add(new Customer(1,"shyam", 1278));
		temp.add(new Customer(2,"yamini", 1000));
		
		List<Customer> res = new ArrayList();
		res.add(new Customer(1,"shyam", 1278));
		res.add(new Customer(2,"yamini", 1000));
		
		when(c_jpa.findAll()).thenReturn(res);
		assertThat(cs.getCustomers()).hasSizeGreaterThan(0).isEqualTo(temp);
	}
	
	@Test
	void getSpecificCustomers() {
		int id = 1;
		Customer expected = new Customer(1,"shyam", 1278);
		Customer result = new Customer(1,"shyam", 1278);
		
		when(c_jpa.getOne(1)).thenReturn(result);
		assertThat(cs.getSpecificCustomer(id)).isEqualTo(expected);
		
	}
	
	@Test
	void getSpecificCustomersNotPresent() {
		int id = 2;
		Customer result = new Customer(1,"shyam", 1278);
		
		when(c_jpa.getOne(1)).thenReturn(result);//by default for other valuess default value "null: is returned
		assertThat(cs.getSpecificCustomer(id)).isEqualTo(null);
	}
	
	
	//Testing for post operations
	
	@Test
	void postCustomerValid() {
		Customer cust = new Customer(12,"vikram",1213214);
		boolean res = cs.postCustomer(cust);
		assertThat(res).isEqualTo(true);
	}
	
	@Test
	void postCustomerNotValid() {	
		Customer cust = new Customer(12,"vikram",-1);
		boolean res = cs.postCustomer(cust);
		assertThat(res).isEqualTo(false);
	}
	
	@Test
	void postCustomerNotValid2() {
		Customer cust = new Customer();
		boolean res = cs.postCustomer(cust);
		assertThat(res).isEqualTo(false);
	}
	
	@Test
	void postCustomerValid_but_database_failed() {
		
		//Input customer details
		Customer cust = new Customer();
		boolean result = cs.postCustomer(cust);
		assertThat(result).isEqualTo(false);
	
	}
	
	//Testing for put operations
	@Test
	void putCustomerValid() {
		
		Customer cust = new Customer(12,"vikram",1213214);
		boolean result = cs.postCustomer(cust);
		assertThat(result).isEqualTo(true);
	}
	@Test
	void putCustomerNotValid() {
			
		Customer cust = new Customer(12,"vikram",-1);
		boolean result = cs.postCustomer(cust);
		assertThat(result).isEqualTo(false);
	}
	@Test
	void putCustomerNotValid2() {
	
		Customer cust = new Customer();	
		boolean result = cs.postCustomer(cust);
		assertThat(result).isEqualTo(false);
	}
	
	@Test
	void putCustomerValid_but_database_failed() {

		//Input customer details
		Customer cust = new Customer();	
		boolean result = cs.postCustomer(cust);
		assertThat(result).isEqualTo(false);
	}
	
	@Test
	void deleteCustomer() {
		int id = 1;
		when(c_jpa.getById(1)).thenReturn(new Customer(1,"vv",121));
		assertThat(cs.deleteCustomer(id)).isEqualTo(true);
	}
	@Test
	void deleteCustomerInvalid() {
		int id = -1111;
		when(c_jpa.getById(1)).thenReturn(new Customer(1,"vv",121));
		assertThat(cs.deleteCustomer(id)).isEqualTo(false);
	}
	@Test
	void deleteCustomerNotExists() {
		int id = 1111;
		when(c_jpa.getById(1)).thenReturn(null);
		assertThat(cs.deleteCustomer(id)).isEqualTo(false);
	}
}

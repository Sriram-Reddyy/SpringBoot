package com.example.demo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Rest_Controller{
	
	@Autowired
	private CustomerService cs;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers(){
		return cs.getCustomers();
	}
	@GetMapping("/customers/{id}")
	public Customer getSpecificCustomers(int id){
		return cs.getSpecificCustomer(id);
	}
	
	
	@PostMapping("/customers")
	public ResponseEntity<HttpStatus> postCustomer(@RequestBody Customer cust){
		if(cs.postCustomer(cust)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
	
	
	@PutMapping(path = "/customers", consumes = "application/json")
	public ResponseEntity<HttpStatus> putCustomer(@RequestBody Customer cust){
		if(cs.putCustomer(cust)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
	
	
	@DeleteMapping(path = "/customers/{id}", consumes = "application/json")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable int id){
		if(cs.deleteCustomer(id)) {
			return new ResponseEntity<HttpStatus>(HttpStatus.OK);
		}
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);
	}
	
	
}

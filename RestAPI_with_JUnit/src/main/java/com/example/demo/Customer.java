package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Customer {
	
	@Id
	private int id;
	private String name;
	private int ssn;
	public Customer() {
		super();
	}
	public Customer(int id, String name, int ssn) {
		super();
		this.id = id;
		this.name = name;
		this.ssn = ssn;
	}
	@Override
    public boolean equals(Object o) {
  
        if (o == this) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        Customer c = (Customer) o;
        boolean value = this.name.compareTo(c.name) == 0 ? true : false;
        return this.id==c.id && this.ssn==c.ssn && value;
         
    }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSsn() {
		return ssn;
	}
	public void setSsn(int ssn) {
		this.ssn = ssn;
	}
}
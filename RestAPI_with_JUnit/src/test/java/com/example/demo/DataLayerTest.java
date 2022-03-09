package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


//@DataJpaTest-->>USe for embedded database
@SpringBootTest
public class DataLayerTest {
	//findAll(), deleteAll() functions are already tested so no need to test them again
	@Autowired
	private Customer_JPA c;
	
	@Test
	void ExitsById() {
		assertThat(c.isPersonExitsById(1)).isEqualTo(true);
	}
	
	@Test
	void notExitsById() {
		assertThat(c.isPersonExitsById(2)).isEqualTo(false);
	}
	
}

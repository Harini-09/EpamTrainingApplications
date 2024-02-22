package com.epam;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootquizApplicationTests {

	@Test
	void contextLoads() {
		boolean a = true;
		assertEquals(true, a);
	}

}
 
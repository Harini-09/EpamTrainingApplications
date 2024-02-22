package com.epam.junitpractise;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SwapLastTwoCharsTest {

	SwapLast2Chars swaplast2chars;

	@BeforeEach
	void getObject() {
		swaplast2chars = new SwapLast2Chars();
	}

	@Test
	void test2chars() {
		String result = swaplast2chars.swap("ab");
		assertEquals("ba", result);
	}

	@Test
	void test4chars() {
		String result = swaplast2chars.swap("Rain");
		assertEquals("Rani", result);
	}

}

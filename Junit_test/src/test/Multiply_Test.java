package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class Multiply_Test {

	@Test
	public void test_Multiply() {
		Junit_test test = new Junit_test();
		int result = test.multiply(3, 4);
		assertEquals(12,result);
	}

}

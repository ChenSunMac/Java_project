package test;

import static org.junit.Assert.*;

import org.junit.Test;

public class Concatenate_test {

	@Test
	public void test() {
		Junit_test test = new Junit_test();
		String result = test.concatenate("one", "two");
		assertEquals("onetwo",result);
	}

}

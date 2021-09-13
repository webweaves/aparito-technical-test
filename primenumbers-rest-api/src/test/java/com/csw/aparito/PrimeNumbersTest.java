package com.csw.aparito;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class PrimeNumbersTest {

	@Test
	public void primeNumbersObjectTest() {
		PrimeNumbers primeNumbers = new PrimeNumbers(Arrays.asList(1,2,3));
		assertEquals(3, primeNumbers.getPrimeNumbers().size());		
	}
}

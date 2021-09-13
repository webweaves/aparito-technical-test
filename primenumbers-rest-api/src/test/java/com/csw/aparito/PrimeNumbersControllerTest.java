package com.csw.aparito;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class PrimeNumbersControllerTest {

	@Test
	public void checkPrimeNumbersGeneration() {
		
		PrimeNumbersController primeNumbersController = new PrimeNumbersController();

		assertEquals("[2, 3, 5]", 
				primeNumbersController.getPrimeNumbers(5).toString());
		
		assertEquals("[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47]", 
				primeNumbersController.getPrimeNumbers(50).toString());
		
	}
}

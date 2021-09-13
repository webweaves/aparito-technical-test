package com.csw.aparito.rest;

import static org.mockito.Mockito.when;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.csw.aparito.PrimeNumbers;
import com.csw.aparito.PrimeNumbersController;

public class PrimeNumbersResourceTest {

	@Spy @InjectMocks
	PrimeNumbersResource primeNumbersResource;

	PrimeNumbersController primeNumbersController;
	
	@Before
	public void initMocks() {
		//mock the primeNumbersController
		primeNumbersController = Mockito.mock(PrimeNumbersController.class);		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testInvalidNumber() {
		assertNull(primeNumbersResource.generatePrimeNumbers("10L"));	
	}
	
	@Test
	public void testAValidNumber() {
		when( primeNumbersController.getPrimeNumbers(10) ).thenCallRealMethod();
		PrimeNumbers primeNumbers = primeNumbersResource.generatePrimeNumbers("10");
		assertEquals("[2, 3, 5, 7]", primeNumbers.getPrimeNumbers().toString());
	}
}

package com.csw.aparito;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.csw.aparito.client.PrimeNumbersRESTClient;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;

import java.util.Arrays;

import static org.mockito.Mockito.verify;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Unit test for frontend controller tests
 */
public class FrontendControllerTest {

    protected FacesContext facesContext;
    protected ExternalContext externalContext;

    private PrimeNumbersRESTClient primeNumbersClient;
    
	@Spy @InjectMocks
	FrontendController frontendController;
    
	@Before
	public void initMocks() {
        externalContext = Mockito.mock(ExternalContext.class);
        facesContext = ContextMocker.mockFacesContext(externalContext);
        
        //mock the client so we can simulate interaction
        primeNumbersClient = Mockito.mock(PrimeNumbersRESTClient.class);
        
        MockitoAnnotations.initMocks(this);
	}
		
	private @Captor ArgumentCaptor<FacesMessage> stringCaptor = ArgumentCaptor.forClass( FacesMessage.class );

	@Test
	public void testNullResponseFromRESTApi() {
		//simulate a null return value
		doReturn(null).when(frontendController).fetchPrimeNumbers(any());
		
		frontendController.setPrimeLimit(10);
		assertEquals("Expected an empty result", "", frontendController.primeNumberClick());
	
		//check an error message was added to the faces context
		verify( facesContext ).addMessage( any(), stringCaptor.capture() );
	}
	
	@Test
	public void testValidResponseFromRESTApi() {
		//simulate a null return value
		doReturn(Arrays.asList(1,2,3)).when(frontendController).fetchPrimeNumbers(any());
		
		frontendController.setPrimeLimit(10);
		assertEquals("Expected an empty string", "", frontendController.primeNumberClick());
	
		assertEquals("Expected 3 entries in primeNumbersResult", 3, frontendController.getPrimeNumbersResult().size());
	}
	
	@Test
	public void testFetchingPrimeNumbers() {

		//simulate a null response/a REST error
		doReturn(null).when(primeNumbersClient).getPrimeNumbersFromRESTendpoint(any());
		assertNull("There should be no prime numbers returned", frontendController.fetchPrimeNumbers(10));

		//simulate a valid REST response
		doReturn(Arrays.asList(1,2,3)).when(primeNumbersClient).getPrimeNumbersFromRESTendpoint(any());
		assertEquals("There should be a list of 3 prime numbers returned", 3, frontendController
				.fetchPrimeNumbers(10)
				.size());
	}
}
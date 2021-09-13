package com.csw.aparito.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import java.io.StringReader;
import java.util.List;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Invocation.Builder;

public class PrimeNumbersClientTest {
	@Spy @InjectMocks
	PrimeNumbersRESTClient primeNumbersClient;
	
	private Client client;
	
	private WebTarget target;
	
	@Before
	public void initMocks() {
		client = Mockito.mock(Client.class);
		target = Mockito.mock(WebTarget.class);
		
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getPrimeNumbersFromRESTendpointTest() {
		
		JsonObject jsonObject = createJsonObject("{\"primeNumbers\":[1,2,3]}");
		
		doNothing().when(primeNumbersClient).buildClient(any());
		
		Builder builder = Mockito.mock(Invocation.Builder.class);		
		doReturn(builder).when(target).request(MediaType.APPLICATION_JSON_TYPE);
		doReturn(jsonObject).when(builder).get(JsonObject.class);
		
		assertEquals("There should be 3 elements present", 
				3, primeNumbersClient.getPrimeNumbersFromRESTendpoint(5).size());
	}
	
	@Test
	public void extractValidJson() {
		JsonObject jsonObject = createJsonObject("{\"primeNumbers\":[1,2,3]}");
		
		List<Integer> result = primeNumbersClient.populatePrimeListFromJson(jsonObject);
		
		assertEquals("There should be 3 elements present", 
				3, result.size());
		
		assertTrue(result.contains(1));
		assertTrue(result.contains(2));
		assertTrue(result.contains(3));
	}
	
	@Test
	public void extractValidJsonNoPrimeNumbers() {
		JsonObject jsonObject = createJsonObject("{\"primeNumbers\":[]}");
		List<Integer> result = primeNumbersClient.populatePrimeListFromJson(jsonObject);
		assertEquals("There should be 0 elements present", 
				0, result.size());
	}

	@Test
	public void extractInvalidJson() {

		JsonObject jsonObject = createJsonObject("{\"wrongAttribute\":[1,2,3]}");
		
		/*
		 * call the JSON parse method with invalid JSON
		 */
		List<Integer> result = primeNumbersClient.populatePrimeListFromJson(jsonObject);
		assertNull("There should be no elements present", result);
	}

	/**
	 * convenience method to create a JSON object
	 * 
	 * @param json
	 * @return
	 */
	private JsonObject createJsonObject(String json) {
		JsonReader jsonReader = Json.createReader(new StringReader(json));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		return jsonObject;
	}
}

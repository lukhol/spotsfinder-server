package com.lukhol.spotsfinder.endpoints;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.lukhol.spotsfinder.email.EmailSender;
import com.lukhol.spotsfinder.endpoint.MobileAppErrorRestEndpoint;
import com.lukhol.spotsfinder.model.MobileAppError;
import com.lukhol.spotsfinder.service.MobileAppErrorService;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest({ MobileAppErrorRestEndpoint.class })
public class ErrorRestEndpointTests {
	
	@Autowired
	private MobileAppErrorRestEndpoint errorRestEndpoint;
	
	@Autowired
    private WebApplicationContext wac;
 
    @Autowired
    private FilterChainProxy springSecurityFilterChain;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MobileAppErrorService errorService;
	
	@MockBean
	private EmailSender emailSender;
	
	private String exampleErrorJson = "{\"errorId\":9,\"deviceInfo\":{\"idiom\":\"Phone\",\"model\":\"Redmi Note 3\",\"version\":\"6.0.1\",\"versionNumber\":\"6.0.1\",\"platform\":\"Android\"},\"className\":\"Error\",\"message\":\"Exception has been thrown by the target of an invocation.\",\"whereOccurred\":\"AndroidCurrentDomain\",\"stackTraceString\":\"StackTraceString\"}";
	
	@Before
	public void setUp(){
		this.mockMvc = 
				MockMvcBuilders
					.webAppContextSetup(this.wac)
					.addFilter(springSecurityFilterChain)
					.build();
	}
	
	@Test
	public void canCreateErrorRestEndpoint(){
		assertThat(errorRestEndpoint).isNotNull();
	}
	
	@Test(expected = NullPointerException.class)
	public void cannotCreateErrorRestEndpoint(){
		errorRestEndpoint = new MobileAppErrorRestEndpoint(null,  null);
	}
	
	//@Test
	public void canPostError() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/errors")
				.header("Authorization", "Basic c3BvdGZpbmRlcjpzcG90ZmluZGVyU2VjcmV0")
				.accept(MediaType.APPLICATION_JSON)
				.content(exampleErrorJson)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		
		Mockito
			.verify(errorService, Mockito.times(1))
			.save(Mockito.any(MobileAppError.class));
		
		Mockito
			.verify(emailSender, Mockito.times(1))
			.sendEmail("lukasz.holdrowicz@gmail.com", "Error json:", exampleErrorJson);
	}
}

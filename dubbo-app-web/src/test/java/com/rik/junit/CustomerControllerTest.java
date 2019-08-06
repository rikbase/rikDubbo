package com.rik.junit;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.rik.dubbo.controller.CustomerController;
import com.rik.dubbo.result.JsonResult;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
@WebAppConfiguration 
public class CustomerControllerTest {
	@Autowired
    CustomerController customerController;

    @Autowired
    ServletContext context;
    MockMvc mockMvc;
    
	@Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }
	
	private JsonResult controllerTest(String uri, String requestMethod, MediaType mediaType, String paramKey, String... paramValues) throws Exception{
		
		
		MockHttpServletRequestBuilder builder = RequestMethod.POST.equals(requestMethod)? MockMvcRequestBuilders.post(uri)
				:MockMvcRequestBuilders.post(uri);
		
		if (builder== null) return new JsonResult("0", "请求方式不支持");
		RequestBuilder requestBuilder=builder.accept(mediaType).param(paramKey,paramValues);
		MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
				.andDo(MockMvcResultHandlers.print())
                .andReturn();
        int status=mvcResult.getResponse().getStatus();  
        String result = mvcResult.getResponse().getContentAsString();
        JSONObject object = JSONObject.parseObject(result);
        return new JsonResult(String.valueOf(status), object);
	}
	
	@Test
	public void testSayHi(){
//		String[] params = {"{}","geo"};
		try {
			System.out.println(controllerTest("/dubbo/service/data", "POST", MediaType.APPLICATION_JSON, "c","inventory"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

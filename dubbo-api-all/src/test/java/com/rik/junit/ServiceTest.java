/**
 * 
 */
package com.rik.junit;



import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rik.dubbo.api.DataSrcService;
import com.rik.dubbo.api.FormModelService;
import com.rik.dubbo.api.MongoService;
import com.rik.dubbo.result.JsonResult;

/**
 * @author xz
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public class ServiceTest {
	@Autowired
	private FormModelService formModelService;
	@Autowired
	private DataSrcService dataSrcService;
	@Autowired
	private MongoService mongoService;
	
	@Test
	public void test() {
	 String name = formModelService.getFormDDL("basic_line_price").get("Table").toString();
	 assertEquals("basic_line_price", name);
	 
	 JsonResult jsonResult = dataSrcService.getInfo("inventory");
	 System.out.println(jsonResult.toString());
		
	 jsonResult = mongoService.find("{}", "inventory");
	 System.out.println(jsonResult);
	}

}

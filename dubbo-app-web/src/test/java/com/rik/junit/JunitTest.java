package com.rik.junit;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rik.dubbo.api.FormModelService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext.xml")
public class JunitTest {
	
	@Autowired
	private FormModelService formModelService;
	@Before//说明在测试函数执行之前会首先执行这个函数 @Before符号必须写
    public void start() {
        System.out.println("Code Start!");
    }

    @After//说明在测试函数执行之后会接着执行这个函数  @After符号必须写
    public void end() {
        System.out.println("Code End!");
    }

    // @Test : 表示这是一个测试用例，只有标识了改符号的函数才会被执行测试
    @Test
    public void test() {
    	String table = formModelService.getFormDDL("basic_line_price").get("Table").toString();
    	assertEquals("basic_line_price", table);
    	System.out.println(table);
    }

}

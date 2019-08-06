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
	@Before//˵���ڲ��Ժ���ִ��֮ǰ������ִ��������� @Before���ű���д
    public void start() {
        System.out.println("Code Start!");
    }

    @After//˵���ڲ��Ժ���ִ��֮������ִ���������  @After���ű���д
    public void end() {
        System.out.println("Code End!");
    }

    // @Test : ��ʾ����һ������������ֻ�б�ʶ�˸ķ��ŵĺ����Żᱻִ�в���
    @Test
    public void test() {
    	String table = formModelService.getFormDDL("basic_line_price").get("Table").toString();
    	assertEquals("basic_line_price", table);
    	System.out.println(table);
    }

}

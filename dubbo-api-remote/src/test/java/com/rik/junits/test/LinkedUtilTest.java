package com.rik.junits.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rik.dubbo.utils.LinkedUtil;
import com.rik.dubbo.utils.Node;

public class LinkedUtilTest {
	
	LinkedUtil linkedUtil = new LinkedUtil();
	Node<String> A = new Node<String>("A");
	Node<String> B = new Node<String>("B");
	Node<String> C = new Node<String>("C");
	Node<String> D = new Node<String>("D");
	Node<String> E = new Node<String>("E");
	Node<String> F = new Node<String>("F");
	
//	@Test
	public void testReverse() {
		
		linkedUtil.reverse(A, null);
	}

//	@Test
	public void testPrint() {
		linkedUtil.print(A);
	}

	@Test
	public void testIsLoop() {
		A.next = B;
		B.next = C;
		C.next = D;
		D.next = E;
		E.next = F;
		F.next = A;
		linkedUtil.isLoop(A);
	}
//	@Test
    public void testTheSameReference1(){
        String str1="abc";
        String str2="abc";
        String str3="ab"+"c";
        String str4=new String(str2);
        
        assertSame(str1,str2);
        assertSame(str1,str3);
        assertSame(str1,str4);
    }
}

package com.rik.dubbo.avl;

public class BSTNode {
	public int data;
	BSTNode parentNode;
	BSTNode leftNode;
	BSTNode rightNode;
	boolean isDel;
	boolean isLeftNode;
	
	
	public BSTNode(int d){
		data = d;
	}
	public void point(){
		System.out.println(data);
	}
}

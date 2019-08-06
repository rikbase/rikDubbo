package com.rik.dubbo.avl;



public interface Tree {
	public BSTNode find(int key);
	public boolean insert(int data);
	public boolean delete(int key);
	public boolean update(int key, int data);
	public void prefix(BSTNode node); //前序遍历
	public void infix(BSTNode node); //中序遍历
	public void posfix(BSTNode node); //后序遍历
	public BSTNode findMin();
	public BSTNode findMax();
}

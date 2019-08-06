package com.rik.dubbo.avl;



public interface Tree {
	public BSTNode find(int key);
	public boolean insert(int data);
	public boolean delete(int key);
	public boolean update(int key, int data);
	public void prefix(BSTNode node); //ǰ�����
	public void infix(BSTNode node); //�������
	public void posfix(BSTNode node); //�������
	public BSTNode findMin();
	public BSTNode findMax();
}

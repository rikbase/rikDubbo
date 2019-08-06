package com.rik.dubbo.avl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class BSTree implements Tree{
	public BSTNode rootNode;
	
	public int depth(BSTNode node){
		if (node==null) return 0;
		int a= depth(node.leftNode)+1;
		int b= depth(node.rightNode)+1;
		return a>b?a:b;
	}
	
	public void print(){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		LinkedList<BSTNode> queue = new LinkedList<BSTNode>();
		if(rootNode == null) return;
		queue.offer(rootNode);
		
		while (!queue.isEmpty()) {
			int count=0;
			int last = queue.size();
			List<Integer> list2 = new ArrayList<Integer>();
			while (count < last) {
				BSTNode current = queue.poll();
				list2.add(current.data);
				count++;
				if (current.leftNode!= null) {
					queue.offer(current.leftNode);
				}
				if (current.rightNode!=null) {
					queue.offer(current.rightNode);
				}
			}
			list.add(list2);
		}
		
		for (int i = 0; i < list.size(); i++) {
			List<Integer> list2 = list.get(i);
			for (int j = 0; j < list2.size(); j++) {
				System.out.print(list2.get(j)+" ");
			}
			System.out.println();
		}
	}
	
	public BSTNode find(int key) {
		// TODO Auto-generated method stub
		BSTNode currentNode = rootNode;
		while (currentNode !=null) {
			if (currentNode.data >key) {
				currentNode = currentNode.leftNode;
			}else if (currentNode.data < key) {
				currentNode=currentNode.rightNode;
			}else {
				return currentNode;
			}
		}
		return null;
	}
	
	public boolean insert(int data) {
		// TODO Auto-generated method stub
		BSTNode node = new BSTNode(data);
		if (rootNode == null) {
			rootNode = node;
			return true;
		}else {
			BSTNode currentNode = rootNode;
			while (currentNode != null) {
				node.parentNode = currentNode;
				if (currentNode.data>data) {
					currentNode = currentNode.leftNode;
					if (currentNode == null) {
						node.isLeftNode =true;
						node.parentNode.leftNode = node;
						return true;
					}
				}else{
					currentNode = currentNode.rightNode;
					if (currentNode == null) {
						node.isLeftNode =false;
						node.parentNode.rightNode = node;
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean delete(int key) {
		// TODO Auto-generated method stub
		
		BSTNode delNode = find(key);
		if (delNode == null) return false;
		//操作节点没有左右节点
		if (delNode.leftNode==null && delNode.rightNode==null) {
			if (delNode==rootNode) {
				rootNode=null;
			}else if (delNode.isLeftNode) {
				delNode.parentNode.leftNode=null;
			}else{
				delNode.parentNode.rightNode=null;
			}
		}else if (delNode.leftNode!=null && delNode.rightNode==null) {
			if (delNode==rootNode) {
				rootNode=delNode.leftNode;
			}else if (delNode.isLeftNode) {
				delNode.parentNode.leftNode=delNode.leftNode;
			}else {
				delNode.parentNode.rightNode=delNode.leftNode;
			}
		}else if (delNode.leftNode==null && delNode.rightNode!=null) {
			if (delNode==rootNode) {
				rootNode=delNode.rightNode;
			}else if (delNode.isLeftNode) {
				delNode.parentNode.leftNode=delNode.rightNode;
			}else {
				delNode.parentNode.rightNode=delNode.rightNode;
			}
		}else {
			BSTNode successor = getSuccessor(delNode);
			if (delNode==rootNode) {
				rootNode=successor;
			}else if (delNode.isLeftNode) {
				delNode.parentNode.leftNode= successor;
			}else {
				delNode.parentNode.rightNode = successor;
			}
			successor.leftNode = delNode.leftNode;
		}
		return false;
	}

	private BSTNode getSuccessor(BSTNode node){
		BSTNode successor = node;
		BSTNode currentNode = node.rightNode;
		while (currentNode != null) {
			successor= currentNode;
			currentNode = currentNode.leftNode;
		}
		if (successor!=node.rightNode) {
			successor = node.leftNode;
		}
		return successor;
	}
	
	public boolean update(int key, int data) {
		// TODO Auto-generated method stub
		BSTNode currentNode = find(key);
		if (currentNode ==null) return false;
		currentNode.data=data;
		return true;
	}

	public void prefix(BSTNode node) {
		// TODO Auto-generated method stub
		if (node == null) return;
		System.out.print(node.data+" ");
		prefix(node.leftNode);
		prefix(node.rightNode);
		
	}

	public void infix(BSTNode node) {
		// TODO Auto-generated method stub
		if (node == null) return;
		infix(node.leftNode);
		System.out.print(node.data+" ");
		infix(node.rightNode);
		
	}

	public void posfix(BSTNode node) {
		// TODO Auto-generated method stub
		if (node == null) return;
		posfix(node.leftNode);
		posfix(node.rightNode);
		System.out.print(node.data+" ");
	}

	public BSTNode findMin() {
		// TODO Auto-generated method stub
		BSTNode currentNode = rootNode;
		BSTNode minNode = null;
		while (currentNode != null) {
			minNode = currentNode;
			currentNode = currentNode.leftNode;
		}
		return minNode;
	}

	public BSTNode findMax() {
		// TODO Auto-generated method stub
		BSTNode currentNode = rootNode;
		BSTNode maxNode = null;
		while (currentNode!=null) {
			maxNode = currentNode;
			currentNode = currentNode.rightNode;
		}
		return maxNode;
	}
}

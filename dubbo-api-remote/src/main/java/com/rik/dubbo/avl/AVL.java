package com.rik.dubbo.avl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Description;

public class AVL {
	public Node root;
	
	//私有节点类
	public class Node{
		public int val;
		public Node left,right;
		public int height=1;
		public Node(int val){
			this.val = val;
		}
	}
	
	private int max(int i, int j){
		return i>j?i:j;
	}
	
	public int height(Node node){
		if(node==null) return 0;
		return node.height;
	}
	
	public Node find(Node node, int val){
		if (node==null) return null;
		if (node.val>val) node.left = find(node.left, val);
		else if (node.val <val) node.right= find(node.right, val);
		else return node;
		return null;
	}
	
	public Node find(int val){
		return find(root,val);
	}
	private Node insert(Node node, int val){
		if(node == null)  return new Node(val);
		if (node.val >= val) node.left=insert(node.left, val);
		else if(node.val < val) node.right=insert(node.right, val);
		node.height=max(height(node.left), height(node.right))+1;
		node =reBalance(node);
		return node;
	}
	
	public void insert(int val){
		root = insert(root, val);
	}
	
	public Node avlMax(Node node){
		if (node.right == null) return node;
		return avlMax(node.right);
	}
	public Node avlMin(Node node){
		if (node.left == null) return node;
		return avlMin(node.left);
	}
	
	private Node rotateRight(Node node){
		Node y= node.left;
		node.left = y.right;
		y.right = node;
		node.height=max(height(node.left),height(node.right))+1;
		y.height=max(height(y.left), height(y.right))+1;
		return y;
		
	}
	
	private Node rotateLeft(Node node){
		Node y = node.right;
		node.right =y.left;
		y.left=node;
		node.height=max(height(node.left),height(node.right))+1;
		y.height=max(height(y.left), height(y.right))+1;
		return y;
	}
	
	private int getBalance(Node node){
		if (node==null) return 0;
		return height(node.left)-height(node.right);
	}
	
	public Node reBalance(Node node){
		int balanceF = getBalance(node);
		//LL
		if (balanceF>1&& getBalance(node.left) > 0) {
			return rotateRight(node);
		}
		//LR
		if (balanceF>1&& getBalance(node.left)<=0) {
			Node t = rotateLeft(node);
			return rotateRight(t);
		}
		//RR
		if (balanceF<-1&& getBalance(node.right)<=0) {
			return rotateLeft(node);
		}
		//RL
		if (balanceF<-1&&getBalance(node.right)>0) {
			Node t = rotateRight(node);
			return rotateLeft(t);
		}
		return node;
	}
	
	@Description("获取继承者")
	private Node getSuccessor(Node node){
		if (node.right ==null && node.left!=null) return node.left;
		node.right =getSuccessor(node.left);
		return node;
	}
	
	public Node getSuccessor(int val){
		return getSuccessor(new Node(val));
	}
	
	private Node delete(Node node, int val){
		if (node==null) return null;
		if (node.val>val) node.left = delete(node.left, val);
		else if(node.val<val) node.right = delete(node.right, val);
		else {
			if (node.left==null) return node.right;
			if (node.right==null) return node.left;
		}
		return node;
	}
	
	public void delete(int val){
		delete(root,val);
	}
	
	public void printAVL(){
		LinkedList<Node> queue = new LinkedList<Node>();
		Node currentNode= root;
		if (currentNode == null) return;
		queue.offer(currentNode);
		int parent=1,child=0;
		while (!queue.isEmpty()) {
			currentNode =queue.poll();
			System.out.print(currentNode.val+" ");
			if (currentNode.left!= null) {
				queue.offer(currentNode.left);
				child++;
			}
			if (currentNode.right!=null) {
				queue.offer(currentNode.right);
				child++;
			}
			parent--;
			if (parent==0) {
				parent =child;
				child=0;
				System.out.println();
			}
		}
	}
	
	public void print(){
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		LinkedList<Node> queue = new LinkedList<AVL.Node>();
		Node current = root;
		if(current == null) return;
		queue.offer(current);
		while (!queue.isEmpty()) {
			int count = 0;
			int size = queue.size();
			List<Integer> list2 = new ArrayList<Integer>();
			while (count < size) {
				current = queue.poll();
				list2.add(current.val);
				count++;
				if (current.left!= null) {
					queue.offer(current.left);
				}
				if (current.right!=null) {
					queue.offer(current.right);
				}
			}
			list.add(list2);
		}
		
		for (int i = 0; i < list.size(); i++) {
			List<Integer> list2 = list.get(i);
			System.out.println();
			for (int j = 0; j < list2.size(); j++) {
				System.out.print(list2.get(j)+" ");
			}
		}
		
	}
	
	public void preSort(Node node){
		if (node == null) return;
		System.out.print(node.val+" ");
		preSort(node.left);
		preSort(node.right);
	}
	
	public void inSort(Node node){
		if (node == null) return;
		inSort(node.left);
		System.out.print(node.val+" ");
		inSort(node.right);
	}
	
	public void posSort(Node node){
		if (node == null) return;
		posSort(node.left);
		posSort(node.right);
		System.out.print(node.val+" ");
	}
	
	public static void main(String[] args) {
		AVL avl = new AVL();
		int[] num = {84,24,100,40,28,47,94,91,25,5};
		for (int i = 0; i <num.length; i++) {
			avl.insert(num[i]);
		}
		System.out.println("\nAVL打印：");
		avl.printAVL();
//		avl.print();
		System.out.println("\n查询到:"+avl.find(25).val);
//		System.out.println("\n二叉树信息：");
//		System.out.println("深度："+avl.height(avl.root)+" 最大值："+avl.avlMax(avl.root).val+" 最小值："+avl.avlMin(avl.root).val);
	}
}

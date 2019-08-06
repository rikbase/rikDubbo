package com.rik.dubbo.utils;

public class Node<E> {
	public E e;
	public Node<E> next;
    public Node(E e){
		this.e = e;
	}
    public void add(E e) {
		// TODO Auto-generated method stub
    	if (next == null) next = new Node<E>(e); else next.add(e);
	}
    
    public Node(){}
    
    public Node<E> getNext(){
    	return next;
    }
    public void setNext(Node<E> next){
    	this.next = next;
    }
    
}

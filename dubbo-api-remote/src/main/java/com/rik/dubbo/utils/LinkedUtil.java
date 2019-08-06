package com.rik.dubbo.utils;

import org.springframework.context.annotation.Description;

public class LinkedUtil {
	
	@Description("������ݹ鷴ת")
	public <E> void reverse(Node<E> node, Node<E> nx){
    	if (node == null) return ;
    	Node<E> next = node.getNext();
    	node.setNext(nx);
    	reverse(next, node);
    }
	
	@Description("������ݹ��ӡ")
	public <E> void print(Node<E> node){
    	System.out.print(node.e+"->");
    	if (node.getNext() != null) print(node.getNext());
    }
	
	@Description("�������ж��Ƿ��Ǳջ�")
	public <E> boolean isLoop(Node<E> node){
		int count=0;
		StringBuffer step = new StringBuffer();
		if (node == null || node.next == null) {
			return false;
		}
		
		Node<E> first = node,second = node;
		while (second!= null && second.next != null && second.next.next !=null) {
			count++;
			first = first.next;
			second = second.next.next;
			step.append(" F:"+first.e+"~S:"+second.e +" ");
			if (first== second) {
				System.out.println("this is closed loop, steps��"+count+"==>{"+step.toString()+"}");
				return true;
			}
		}
		return false;
	}
}

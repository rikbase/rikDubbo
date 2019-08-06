package com.rik.dubbo.utils;

import com.rik.dubbo.avl.AVL;

public class BSTreeTest {
	private static int length=100;
	static Integer[] arr= new Integer[length];
	static{
		for (int i = 0; i < length; i++) {
			arr[i]=(int) (Math.random()*10+1);
		}
	}
	public void sort(){
		for (int i = 0, j=length-1; i < arr.length; i++) {
			//��ǰֵ������0����Ҫ�ͺ�ֵ����λ�ã�
			if (arr[i]<=0){
				j=getJ(i,j);
				arr[i]=arr[i]+arr[j];
				arr[j]=arr[i]-arr[j];
				arr[i]=arr[i]-arr[j];
				j--;
			} 
		}
	}
	
	private int getJ(int i, int j){
		if (i<j&&arr[j]>0) {
			return j;
		}else {
			j--;
			return getJ(i, j);
		}
		
	}
	
	public static void main(String[] args) {
		AVL avl = new AVL();
		int[] data = {29,44,95,94,84,48,87,13,13,36};
		for (int i = 0; i<data.length; i++) {
			System.out.print(data[i]+",");
			avl.insert(data[i]);
		}
		System.out.println("\nAVL��ӡ��");
		avl.printAVL();
		
		avl.preSort(avl.root);
		System.out.println();
		avl.inSort(avl.root);
		System.out.println();
		avl.posSort(avl.root);
//		avl.print();
//		System.out.println("\n�̳���:"+avl.getSuccessor(40).val);
//		System.out.println("\n��������Ϣ��");
//		System.out.println("��ȣ�"+avl.height(avl.root)+" ���ֵ��"+avl.avlMax(avl.root).val+" ��Сֵ��"+avl.avlMin(avl.root).val);
	}

}

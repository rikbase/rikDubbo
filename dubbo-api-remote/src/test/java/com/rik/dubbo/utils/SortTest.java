package com.rik.dubbo.utils;

import com.rik.dubbo.algorithm.CommonSort;

public class SortTest {
	static volatile double[] arr = new double[5];
	private SortTest(){};
	public static void initArr(){
		for (int i = 0; i < arr.length; i++) {
			double a= ArithUitl.round(Math.random()*100, 3);
			System.out.println(a);
			arr[i]= a;
		}
	}
	
	public static void main(String[] args) {
		CommonSort commonSort = new CommonSort();
		initArr();
		commonSort.print(commonSort.bubbleSort(arr));
		commonSort.print(commonSort.selectSort(arr));
		commonSort.print(commonSort.hillSort(arr));
	}
}

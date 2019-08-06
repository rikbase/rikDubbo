package com.rik.dubbo.algorithm;

import com.rik.dubbo.utils.ArithUitl;

public class CommonSort {
	//øÏÀŸ≈≈–Ú
	public double[] quickSort(double[] arr){
		if (arr.length==0) return arr;
		return arr;
	}
	public double[] bubbleSort(double[] arr){
		System.out.println("\n-- bubbleSort£∫");
		if (arr.length==0) return arr;
		for (int i = 0; i < arr.length-1; i++) {
			for (int j = i+1; j < arr.length; j++) {
				if (arr[i]>arr[j]) {
					arr[i]=ArithUitl.add(arr[i], arr[j]);
					arr[j]=ArithUitl.sub(arr[i], arr[j]);
					arr[i]=ArithUitl.sub(arr[i], arr[j]);
				}
			}
		}
		
		return arr;
	}
	
	public double[] insertSort(double[] arr){
		System.out.println("\n-- insertSort:");
		if (arr.length==0) return arr;
		double current;
		for (int i = 0; i < arr.length-1; i++) {
			current=arr[i+1];
			int preIndex=i;
			while (preIndex>=0&&arr[preIndex]>current) {
				arr[preIndex+1] = arr[preIndex];
				preIndex--;
			}
			arr[preIndex+1]=current;
		}
		return arr;
	}
	
	public double[] hillSort(double[] arr){
		System.out.println("\n-- hillSort:");
		if (arr.length==0) return arr;
		double current;
		int step = arr.length/2;
		while (step > 0) {
			for (int i = step; i < arr.length; i++) {
				current=arr[i];
				int preIndex=i-step;
				while (preIndex>=0 && arr[preIndex]>current) {
					arr[preIndex+step] = arr[preIndex];
					preIndex=preIndex-step;
				}
				arr[preIndex+step]=current;
			}
			step=step/2;
		}
		
		return arr;
	}
	public double[] selectSort(double[] arr){
		System.out.println("\n-- selectSort:");
		if (arr.length==0) return arr;
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i; j<arr.length; j++) {
				if (arr[j]>arr[minIndex]) minIndex=j;
			}
			double temp = arr[minIndex];
			arr[minIndex] = arr[i];
			arr[i]=temp;
		}
		
		return arr;
	}
	
	public void print(double[] array){
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+",");
		}
	}
}

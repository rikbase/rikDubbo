package com.rik.junits.test;

import org.junit.Test;

public class BeanTest {

	static Integer[] arr = new Integer[Integer.MAX_VALUE/64]; //31M长度
	public static void sort() {
    	
    	for (int i = 0, j=arr.length-1; i <= j; i++) {
    		if(arr[i]<=0){ //前面的值不大于0 ，看后边的值；
    			//通过递归方式发现从后边开始第几位可以与当前前面的值进行互换；
    			j = jd(i,j);
    			//交换
    			arr[i] =arr[i]+arr[j];
    			arr[j] =arr[i]-arr[j];
    			arr[i] =arr[i]-arr[j];
    			j--;
    		}
		}
    }
//	@Test
//	public void testFinal(){
//		final byte a = 1;
//		final byte b = 2;
//		System.out.println(Math.round(-1.5));
//	}
	/**递归方式实现字符串反转*/
	public String reverse(String str){
		System.out.println(str);
		int length = str.length();
		if (str.length() <= 1) return str;
		String left = str.substring(0, length / 2);
		String right = str.substring(length / 2, length);
		return reverse(right) + reverse(left);
	}
	
	public static void sortS(){
		for (int i = 0, j=arr.length-1; i <= j; i++) {
    		if(arr[i]<=0){ //前面的值不大于0 ，看后边的值；
    			//通过递归方式发现从后边开始第几位可以与当前前面的值进行互换；
    			int start = arr[i];
    			j = jd(i,j);
    			//交换
    			arr[i] =arr[j];
    			arr[j] =start;
    			j--;
    		}
		}
	}
	
    public static int jd(int i, int j){
    	if(i<j&&arr[j] <=0){
    		j--;
			return jd(i,j);
    	}else{
    		return j;
    	}
    	
    }
    
    @Test
    public void text() {
//    	for (int i = 0; i < arr.length; i++) {
//    		arr[i] = (int) ((Math.random()-Math.random())*100+1);
//		}
//    	sort();
    	reverse("abcdefghijklmnopqrstuvwxyz");
    }
    	
}

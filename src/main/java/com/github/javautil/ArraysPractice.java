package com.github.javautil;

import java.util.Arrays;
import java.util.List;





public class ArraysPractice {

	public static void main(String[] args) {
		Integer a[] = {1,2,3,4};
		List<Integer> list = Arrays.asList(1,2,3,4);
		System.err.println(list);
		List<Integer> list2 = Arrays.asList(a);
		System.err.println(list2);
		
		
		int binarySearch = Arrays.binarySearch(a, 15);
		System.err.println(binarySearch);
		
		int[] b = {2,5,7,8,90};
		int[] c = {1};
		System.err.println(Arrays.binarySearch(b, 7));
		System.err.println(myBinarySearch(b, 8));
		System.err.println(myBinarySearch(c, 0));
		
		System.err.println("///////////////");
		myQuickSort(b, 0, b.length-1);
		System.err.println(b);
		
		
		

	}
	
	
	public static int myBinarySearch(int[] a, int key) {
		
		int low = 0;
		int high = a.length - 1;
		int mid = (low + high)>>>1;
		
		while (low <= high) {
			mid = (low + high) >>> 1;
			if (a[mid] == key) {
				return mid;
			}
			
			if (key < a[mid]) {
				high = mid - 1;
			}else {
				low = mid + 1;
			}
		}
		return - (low + 1);
		
	}
	//3  2,5,6,5
	public static void myQuickSort(int[] a,int fromIndex, int endIndex) {
		int i = fromIndex;
		int j = endIndex;
		int p = a[i];
		while ( i < j) {
			while (a[j] > p){
				j--;
			}
			
			if (i <j) {
				a[i] = a[j];
				i++;
			}
			
			while (a[i] < p) {
				i++;
			}
			
			if (i < j) {
				a[j] = a[i];
				j--;
			}
		}
		
		a[i] = p;
		myQuickSort(a, fromIndex, i-1);
		myQuickSort(a, i+1, endIndex);
	}

}

package Sort;

import java.util.Arrays;

class test {
	public static void main(String[] arg) {
		int arr[] = { 29, 78, 45, 23, -5, 7, 23 };
		Sort(arr);
		System.out.print(Arrays.toString(arr));
	}

	private static void Sort(int[] arr) {
		quick_Sort(arr,0,arr.length-1);
	}
	

	private static void quick_Sort(int[] arr, int left, int right) {
		// TODO Auto-generated method stub
		int L=left;
		int R=right;
		int pivot=arr[(left+right)/2];
		while(L<R) {
			if(arr[L]<pivot) {
				L++;
			}
			if(arr[R]>pivot) {
				R--;
			}
			if(R==L) {
				break;
			}
			//交换位置
			Swap(arr,L,R);
			
			if(arr[L]==pivot) {
				R--;
			}
			if(arr[R]==pivot) {
				L++;
			}
		}
		if(R==L) {
			R--;
			L++; //越过边界
		}
		if(R>left) {
			quick_Sort(arr,left,R);
		}
		if(L<right) {
			quick_Sort(arr,L,right);
		}
		
	}

	private static void Swap(int[] arr, int L, int R) {
		// TODO Auto-generated method stub
		int temp;
		temp = arr[L];
		arr[L] = arr[R];
		arr[R] = temp;
	}

}
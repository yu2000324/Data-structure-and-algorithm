package Sort;

public class Merge_Sort {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 4, 6, 8, 5, 9 ,98,45,2,7};
		Sort(arr, arr.length);
		for(int value:arr) {
			System.out.print(value+ " ");
		}
	}

	private static void Sort(int[] arr, int length) {
		// TODO Auto-generated method stub
		int[] temp=new int[arr.length];
		MSort(arr,temp,0,arr.length-1);
	}

	private static void MSort(int[] arr,int[] temp,int L,int rightEnd) {
		// TODO Auto-generated method stub
		int center;
		if(L<rightEnd) {
			center=(L+rightEnd)/2;
			MSort(arr,temp,L,center);
			MSort(arr,temp,center+1,rightEnd);
			Merge(arr,temp,L,center+1,rightEnd);
		}
		
	}

	/**
	 * 
	 * @param arr      传入的数组
	 * @param temp     用于合并的临时数组
	 * @param L        left的起始下标
	 * @param R        rigth的起始下标
	 * @param rightEnd 右边的数组的结束下标
	 */
	private static void Merge(int[] arr, int[] temp, int L, int R, int rightEnd) {
		int leftEnd = R - 1; // 左边终点位置，假设左右两列紧紧挨着
		int tmp = L; // 存放结果的数组的初始位置
		int numElements = rightEnd - L + 1; // 元素总个数
		while (L <= leftEnd && R <= rightEnd) {
			if (arr[L] <= arr[R])
				temp[tmp++] = arr[L++];
			else
				temp[tmp++] = arr[R++];
		}
		while (L <= leftEnd) {
			temp[tmp++] = arr[L++];
		}

		while (R <= rightEnd) {
			temp[tmp++] = arr[R++];
		}
		for (int i = 0; i < numElements; i++, rightEnd--) {
			arr[rightEnd]=temp[rightEnd];
		}
	}
	
	

}

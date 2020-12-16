package Search;

public class BinarySearchNoRecur {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr= {1,2,3,4,5};
		int index=binarySearch(arr,5);
		System.out.print(index);
	}
	
	/**
	 * @param arr		传入的数组
	 * @param target	查找目标值
	 * @return			返回对应下标
	 */
	
	public static int binarySearch(int[] arr,int target) {
		int left=0;
		int right=arr.length-1;
		while(left<=right) {
			int mid=(left+right)/2;
			if(arr[mid]==target) {
				return mid;
			}
			if(target>arr[mid]) {
				left=mid+1;
			}
			if(target<arr[mid]) {
				right=mid-1;
			}
		}
		return -1;
	}

}

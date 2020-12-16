package Sort;

import java.util.Arrays;

/**
 * @author god-jiang
 * @date 2020/1/12
 */
//时间复杂度O(n*logn)，空间复杂度O(n*logn)
public class Quick_Sort {
	public static void main(String[] args) {
		int arr[] = { 5, 1, 3, 23, 45,78, 28};
		Sort(arr);
	}

	private static void Sort(int[] arr) {
		// TODO Auto-generated method stub
		quickSort(arr, 0, arr.length - 1);
		System.out.print(Arrays.toString(arr));
	}

	public static void quickSort(int[] arr, int left, int right) {
		int L = left;
		int R = right;
		// pivot 中轴值
		int pivot = arr[(left + right) / 2];
		// while循环的目的是让比pivot 值小的放左边，大的放右边
		while (L < R) {
			// 在pivot左边一直找，大于或等于pivot就退出
			while (arr[L] < pivot) {
				L++;
			}
			while (arr[R] > pivot) {
				R--;
			}
			// 如果l>=r说明pivot的左右两边的值，已经符合条件了
			if (L == R) {
				break;
			}
			// 交换
			Swap(arr, L, R);
			// 交换完后发现这个arr[L] ==pivot 相等--，前移动，不做这步会导致和pivot一直交换导致死循环
			//一定是对面的指针先动，这里和后面代码指针移动做递归密切相关
			if (arr[L] == pivot) {
				R--;
			}

			if (arr[R] == pivot) {
				L++;
			}
		}
		// 如果L==R，必须让L++，否则出现栈溢出
		if (L == R) {
			L++;
			R--;
		}
		if (left < R) {
			quickSort(arr, left, R);
		}
		if (right > L) {
			quickSort(arr, L, right);
		}
	}

	public static void Swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

package Sort;

import java.util.Arrays;

/**
 * 堆排序
 * 
 * @author yu
 *
 */
public class Heap_Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[] = { 4, 6, 8, 5, 9 };
		Sort(arr);
	}

	private static void Sort(int[] arr) {
		// TODO Auto-generated method stub
		int temp = 0;
		System.out.println("堆排序");
		// 分步完成
//		createHeap(arr,1,arr.length);
//		createHeap(arr,0,arr.length);
		// 完成最终代码
		// 将无序列构建成一个堆，根据升序降序选择大顶堆和小顶堆
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			createHeap(arr, i, arr.length);
		}
		System.out.print(Arrays.toString(arr));
		/*
		 * 将堆顶元素与末尾元素交换，将最大元素沉到数组末端
		 * 重新调整结构，使其满足堆定义，然后继续减缓栈顶元素与当前末尾元素，反复执行调整+加交换步骤，直到整个序列有序
		 */

		for (int j = arr.length - 1; j > 0; j--) {
			// 交换
			temp = arr[j];
			arr[j] = arr[0]; // 最大值和最后一个交换
			arr[0] = temp;
			createHeap(arr, 0, j);
		}
		System.out.print(Arrays.toString(arr));
	}

	/**
	 * 将一个数组（二叉树），调整成一个大顶堆 功能：完成将以i 对应的非叶子节点的树调整成大顶堆 举例：int arr[]= {4,6,8,5,9}; =>
	 * i= 1 => createHeap得到 => 得到{4,9,8,5,6} 如果我们再次调用crateHeap传入的是 i = 0
	 * =>得到{4,9,8,5,6} => {9,6,8,5,4}
	 * 
	 * @param arr    待调整的数组
	 * @param i      表示非叶子节点在数组中的索引
	 * @param length 数组长度,length是在逐渐的减少
	 */
	private static void createHeap(int arr[], int i, int length) {
		// TODO Auto-generated method stub
		// 先取出当前元素的值，保存在临时变量
		int temp = arr[i];
		// 开始调整,一直取左子节点取到最后
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
			if (k + 1 < length && arr[k] < arr[k + 1]) { // 说明左子节点的值小于右子节点的值，k+1保证右子节点不为空
				k++; // 让k指向右子节点
			}
			if (arr[k] > temp) { // 如果子节点大于父节点，还进行调整
				arr[i] = arr[k]; // 把较大的值赋值给当前节点
				i = k; // i指向k，继续循环比较
			} else {
				/**
				 * 这里可以break是因为从非叶子节点最后一层最大值上浮，所以当更上层
				 * 当非叶子节点下来比较时，连它们最大的都比不过，那就没必要往下比了，相当于降纬打击。
				 */
				break;
			}
		}
		// 当for循环结束后，我们已经将i 为父节点的树最大值，放在了最顶上（局部）
		arr[i] = temp;// 把temp放值到调整后的位置
	}
}

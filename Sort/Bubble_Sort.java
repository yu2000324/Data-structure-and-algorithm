package Sort;

/**
 * 冒泡排序算法
 * @author yu
 *
 */
public class Bubble_Sort {
	public static void main(String[] arg) {
		int[] arrary = { 10, 20, 5, 9, 40, 26, 38 };
		Sort(arrary, arrary.length);
		for (int value : arrary) {
			System.out.print(" " + value + " ");
		}
	}

	private static void Sort(int[] arrary, int length) {
		// i定义为要排序数组的次数length-1并且是每次结束内层循环的下标
		for (int i = length - 1; i > 0; i--) {
			// 从数组从头到尾开始比较并且开始交换
			// 设置一个标志位用于记录数组是否排序好
			int flag = 0;
			for (int j = 0; j < i; j++) {
				if (arrary[j] > arrary[j + 1]) {
					int temp = arrary[j];
					arrary[j] = arrary[j + 1];
					arrary[j + 1] = temp;
					flag = 1;
				}
			}
			// 如果在某一次循环中没有产生一次交换就可以认为该数组已经有序，可以直接break了
			if (flag == 0)
				break;
		}
	}
}

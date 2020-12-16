package Sort;

/**
 * 插入排序算法
 * @author yu
 *
 */
public class Insert_Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arrary = { 20, 10, 15, 3, 49, 45, 7 };
		Sort(arrary, arrary.length);
		for (int value : arrary) {
			System.out.print(" " + value + " ");
		}
	}

	private static void Sort(int[] arrary, int length) {
		// TODO Auto-generated method stub
		// 从下标为1开始循环
		for (int i = 1; i < length ; i++) {
			// 取出为i的下一个元素
			int compare = arrary[i];
			/*
			 * 从后往前获取取出元素的前一个元素并且让它和取出的元素进行比较 如果数组大于取出的元素就移出取出元素空位置给前一个元素并且覆盖取出下标的元素
			 */
			int j;
			for (j = i; j >= 1 && arrary[j - 1] > compare; j--) {
				// 移出空位覆盖取出下标的元素
				arrary[j] = arrary[j-1];
			}
			// 把取出的元素放进去
			arrary[j] = compare;
		}

	}

}

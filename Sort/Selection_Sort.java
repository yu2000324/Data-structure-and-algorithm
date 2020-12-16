package Sort;

/**
 * 选择排序算法
 * 
 * @author yu
 *
 */
public class Selection_Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arrary = { 81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15 };
		Sort(arrary, arrary.length);
		for (int value : arrary) {
			System.out.print(" " + value + " ");
		}

	}

	private static void Sort(int[] arrary, int length) {
		// TODO Auto-generated method stub
		//从数组第一个数字开始到最后一个数字的循环
		for (int i = 0; i < length - 1; i++) {
			//声明索引下标和最大值
			int index = 0;
			int Max = Integer.MAX_VALUE;
			//循环找出最小值
			for (int j = i; j < length; j++) {
				if (arrary[j] < Max) {
					index=j;
					Max=arrary[j];
				}
			}
			//执行两个数字的交换
			int tmp;
			tmp=arrary[i];
			arrary[i]=arrary[index];
			arrary[index]=tmp;
		}

	}

}

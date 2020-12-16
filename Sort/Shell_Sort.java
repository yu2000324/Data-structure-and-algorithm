package Sort;

public class Shell_Sort {

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
		for (int D = length / 2; D > 0; D = D / 2) { /* 希尔增量序列 */
			//以增量为1的循环挨个进行比较
			for (int i = D; i < length; i++) {		/*插入排序算法*/
				int compare = arrary[i];			
				int j;
				//间隔为D进行循环
				for (j = i; j >= D && arrary[j - D] > compare; j = j - D) {
					arrary[j] = arrary[j - D];
				}
				arrary[j] = compare;
			}

		}

	}

}

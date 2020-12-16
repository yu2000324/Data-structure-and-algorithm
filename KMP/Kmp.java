package KMP;

public class Kmp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] pattern = { 'A', 'B', 'A', 'B', 'C', 'A', 'B', 'A', 'A' };
		char[] text = { 'A', 'B', 'A', 'B', 'A', 'B', 'C', 'A', 'B', 'A', 'A', 'B', 'A', 'B', 'A', 'B', 'A', 'B' };
//		kmp_search(text,pattern);
		int[] prefix = new int[9];
		
		int n = prefix.length;
		prefix_table(pattern, prefix, n);
//		move_prefix_table(prefix);
		for (int i = 0; i < prefix.length; i++) {
			System.out.print(prefix[i] + " ");
		}
	}

	private static void move_prefix_table(int[] prefix) {
		// TODO Auto-generated method stub
		for (int i = prefix.length - 1; i > 0; i--) {
			prefix[i] = prefix[i - 1];
		}
		prefix[0] = -1;
	}

	public static void kmp_search(char[] text, char[] pattern) {
		int i = 0;
		int j = 0;
		int n = pattern.length;
		int m=text.length;
		int[] prefix = new int[n];
		prefix_table(pattern, prefix, n);
		move_prefix_table(prefix);

		// text[i] len(text)=m
		// pattern[j] len(pattern)=n;
		while (i < m) {
			if (j == n - 1 && text[i] == pattern[j]) {
				System.out.printf("Found pattern at %d\n", i - j);
				j = prefix[j];
			}
			if (text[i] == pattern[j]) {
				i++;
				j++;
			} else {
				j = prefix[j];
				if (j == -1) {
					i++;
					j++;
				}
			}
		}

	}

	public static void prefix_table(char[] pattern, int[] prefix, int n) {
		prefix[0] = 0; // 初始化第一个数字为0的最长前缀
		int len = 0; // 指向数组pattern的下标,当前下标会回溯
		int i = 1; // 遍历整个数组的下标从1开始
		while (i < n) {
			if (pattern[i] == pattern[len]) {
				len++;
				prefix[i] = len;
				i++;
			} else {
				if (len > 0) {
					len = prefix[len - 1];
				} else {
					prefix[i] = len;
					i++;
				}
			}
		}
	}

}

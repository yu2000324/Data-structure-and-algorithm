package Recursion;

public class Queue8 {

	
	//先定义一个max表示共有多少个皇后
	int max=8;
	//定义数组array，保存放置皇后位置的结果，比如arr[8] ={0,4,7,5,2,6,1,3}
	int[] array=new int[max];
	int counts=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue8 queue=new Queue8();
		queue.check(0);
		System.out.print(queue.counts);
	}
	
	
	//编写一个方法，放置第n个皇后
	private void check(int n) {
		if(n==max) {
			print();
			counts++;
			return;
		}
		for(int i=0;i<max;i++) {
			//先把当前的皇后n放到改行的第一个位置
			array[n]=i;
			//判断当放置第n个皇后到i列时候，是否冲突
			if(judge(n)) {
				check(n+1);
			}
		}
		
	}
	
	
	//查看当我们放置第n个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
	/**
	 * @param n		n表示第n个皇后
	 * @return
	 */
	private boolean judge(int n) {
		for(int i=0;i<n;i++) {
			//1.array[i] ==array[n] 表示判断 第n个皇后是否和前面第n-1个皇后同一列
			//Math.abs(n-i)==Math.abs[array[n]-array[i])表示判断第n个厚是否和第i个皇后在同一斜线
 			if(array[i]==array[n]||Math.abs(n-i)== Math.abs(array[n]-array[i])) {
				return false;
			}
		}
		return true;
	}
	
	//写一个方法，可以将皇后摆放的位置打印出来
	public void print() {
		for(int i=0;i<array.length;i++) {
			System.out.print(array[i]+" ");
		}
		System.out.println();
	}

}

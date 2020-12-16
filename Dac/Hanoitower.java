package Dac;

public class Hanoitower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		hanoiTower(6,'A','B','C');
	}
	
	//汉诺塔的移动方法
	//使用分治算法
	
	public static  void hanoiTower(int num,char a ,char b,char c) {
		if(num==1) {
			System.out.println("第1个盘从"+a+"->"+c);
		}else {
			//如果我们有n>=2情况，我们总可以看做分成两部分，最底下1层，和上面的全部，因此上面的全部可以认为是num-1
			//例如有三个盘子，分解成上面2个和底下1个 3-1=2 首先处理2个盘子的移动到b柱子上的问题，再处理移动把最底下盘子移动到
			//c柱子上的问题，最后处理b柱子移动到c柱子上的问题
			hanoiTower(num-1,a,c,b);
			System.out.println("第"+num+"个盘从"+a+"->"+c);
			hanoiTower(num-1,b,a,c);
		}
		
	}

}

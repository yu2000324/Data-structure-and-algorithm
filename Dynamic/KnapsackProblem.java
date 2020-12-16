package Dynamic;

public class KnapsackProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] w = { 1, 4, 3 };// 物品重量
		int[] val = { 1500, 3000, 2000 };// 物品的价值
		int m = 4; // 背包容量
		int n = val.length; // 物品的个数

		// 创建二维数组
		// v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
		int[][] v = new int[n + 1][m + 1];

		// 为了记录放放入商品的情况，我们定一个二维数组
		int[][] path = new int[n + 1][m + 1];

		// 初始化第一行第一列,在这里可以不去处理，默认是0
		for (int i = 0; i < v.length; i++) {
			v[i][0] = 0; // 将第一列是设置为0
		}
		for (int i = 0; i < v[0].length; i++) {
			v[0][i] = 0; // 将第一行设置为0
		}

		// 根据前面得到的递推公式来写
		for (int i = 1; i < v.length; i++) {// 不处理第一行，i是物品编号
			for (int j = 1; j < v[0].length; j++) {// 不处理第一列,j是背包容量大小
				// 公式
				if (w[i - 1] > j) { // i-1是因为要拿i去和w数组里面的重量相关联，而i是从1开始的
					v[i][j] = v[i - 1][j];// 加入当前物品的容量超过了所有的背包容量，直接选择不放
				} else {
					// 因为我们的i，从1开始的，因此公式需要调整成i-1；
					v[i][j] = Math.max(v[i - 1][j], val[i - 1] + v[i - 1][j - w[i - 1]]);
					// 为了记录商品存放到背包的问题，我们不能简单的使用公式
					if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
						v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
						// 把当前的情况记录到path
						path[i][j] = 1;
					} else {
						v[i][j] = v[i - 1][j];
					}
				}
			}
		}

		for (int i = 0; i < v.length; i++) {
			for (int j = 0; j < v[i].length; j++) {
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}

		// 输出最后我们是放入的哪些商品
//		for (int i = 0; i < path.length; i++) {
//			for (int j = 0; j < path[i].length; j++) {
//				if(path[i][j]==1) {
//					System.out.printf("第%d个商品放入到背包\n",i);	
//				}
//			}
//		}
		
		for (int i = 0; i < path.length; i++) {
			for (int j = 0; j < path[i].length; j++) {
					System.out.print(path[i][j]+ " ");	
			}
			System.out.println();	
		}
		int i = path.length - 1; // 行的最大下标值
		int j = path[0].length - 1; // 列的最大下标
		while (i > 0 && j > 0) { // 逆向遍历，从最后开始找
			if(path[i][j]==1) {
				System.out.printf("第%d个商品放入到背包\n",i);
				j-=w[i-1];	//w[i-1] 当前物品是放入背包中的，所有减去自身的重量后再找最优解的放入情况
			}
			i--;
		}
	}

}

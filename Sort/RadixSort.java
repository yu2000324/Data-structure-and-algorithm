package Sort;

import java.util.Arrays;

public class RadixSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[]= {53,3,542,748,14,214};
		Sort(arr);
		System.out.print(Arrays.toString(arr));
	}
	public static void Sort(int[] arr) {
		//得到最大数
		int max=arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i]>max) {
				max=arr[i];
			}
		}
		//得到最大数的长度
		int maxLength=(max+"").length();
		//定义一个二位数组的桶
		int[][] bucket=new int[10][arr.length];
		//比如：bucketCounts[0],记录的就是bucket[0]桶中的数据个数
		int[] bucketElementCounts=new int[10];
		for(int i=0,n=1;i<maxLength;i++,n*=10) {
			//针对每个元素对应的位置进行处理
			for(int j=0;j<arr.length;j++) {
				// 取出每个元素的个数
				int digitOfElement=arr[j]/n%10;
				//放入到对应的桶中
				bucket[digitOfElement][bucketElementCounts[digitOfElement]]=arr[j];
				bucketElementCounts[digitOfElement]++; 
			}
			int index=0;
			//遍历每一个桶
			for(int k=0;k<bucketElementCounts.length;k++) {
				//如果桶中有数据，才把数据放回到原数组
				if(bucketElementCounts[k]!=0) {
					//循环该桶即K个桶，放入
					for(int l=0;l<bucketElementCounts[k];l++) {
						//取出元素放入arr中
						arr[index++]=bucket[k][l];
					}
				}
				bucketElementCounts[k]=0;
			}
		}
		
	}

}

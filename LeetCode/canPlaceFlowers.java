package LeetCode;

public class canPlaceFlowers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] flowerbed = {0,1,0,1,0,1,0,0};
		int n = 1;
		canPlaceFlowers(flowerbed, n);
	}

//	public static boolean canPlaceFlowers(int[] flowerbed, int n) {
//		int i = 0, count = 0;
//		while (i < flowerbed.length) {
//			//此处if进行条件拆分，flowerbed[i] == 0 这个是所有条件的前提
//			//i == 0 || flowerbed[i - 1] == 0 当i==0时候直接符合条件
//			//i == flowerbed.length - 1 || flowerbed[i + 1] == 0 当i==末尾时候也直接符合条件
//			if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0)
//					&& (i == flowerbed.length - 1 || flowerbed[i + 1] == 0)) {
//				flowerbed[i++] = 1;
//				count++;
//			}
//			if (count >= n)
//				return true;
//			i++;
//		}
//		return false;
	
//	}
	
	
    public static boolean  canPlaceFlowers(int[] flowerbed, int n) {
        int length=flowerbed.length;
        int count=0;
        for(int i=0;i<length;i++){
            if(flowerbed[i]==0&&(i==0||flowerbed[i-1]==0)&&
            (i==length-1||flowerbed[i+1]==0)){
                flowerbed[i++]=1;
                count++;
            }
            if(count>=n) return true;
        }
        return false;
    }

}

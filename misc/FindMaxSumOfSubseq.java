package misc;

public class FindMaxSumOfSubseq {

	static int findMaxSumOfSubseq(int[] input, int[] pos){
		int maxValue = Integer.MIN_VALUE;
		int currentSum = 0;
		int flag = 0;
		
		if(input.length == 0)
			return 0;

		for(int i=0; i<input.length; ++i){
			currentSum += input[i];
			if(currentSum <= 0){
				if(currentSum > maxValue){
					maxValue = currentSum;
					pos[0] = i;
					pos[1] = i;
				}	
				currentSum = 0;
				flag = 0;
				continue;
			}
			
			if(currentSum > maxValue){
				maxValue = currentSum;
				pos[1] = i;
				if(flag == 0){
					pos[0] = i;
					flag = 1;
				}
			}
		}
		return maxValue;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = {2, -2, 3, -4, 5, 8, -2, 7, -5, 1};
		//int[] input1 = {-2, -1, 1, -3};
		int[] pos = {0, 0};
		
		int maxSum = findMaxSumOfSubseq(input, pos);
		System.out.println(maxSum);
		System.out.println(pos[0]);
		System.out.println(pos[1]);
	}

}

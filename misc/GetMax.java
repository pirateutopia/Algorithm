package misc;

public class GetMax {

	int[] a;
	static int getMax(int n){
		if(n == 1)
			return 1;
		int max = 0;
		int bound = n/2;
		int temp = 0;
		
		for(int i=1; i<=bound; ++i){
			if(i * (n-i) > max)
				max = i * (n - i);
			
			temp = getMax(n-i) * i;
			if(temp > max)
				max = temp;
		}
		return max;
	}
	
	static int getMaxDP(int n){
		if(n == 1)
			return 0;
		if(n == 2)
			return 1;
		int dp[] = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;
		for(int i=3; i<n+1; ++i){
			int max = 1;
			for(int j=1; j<4; ++j){
				if(j * (i-j) > max)
					max = j*(i-j);
				if(j * dp[i-j] > max)
					max = j*dp[i-j];
			}
			dp[i] = max;
		}
		return dp[n];
	}
	
	static int getMaxOone(int n){
		if(n == 1)
			return 0;
		if(n == 2)
			return 1;
		int mod = n % 3;
		int quotient = n / 3;
		int max = 1;
		if(mod == 1){
			max *= 4;
			quotient --;
		}
		else if(mod == 2)
			max *= 2;
		while(quotient > 0){
			max *= 3;
			quotient --;
		}
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getMaxDP(13));
	}

}

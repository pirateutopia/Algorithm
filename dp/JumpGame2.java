package dp;

public class JumpGame2 {
	public static int jump(int[] A) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        int len = A.length;
        int far = len;
        int[] steps = new int[len];
        steps[len-1] = 0;
        
        for(int i=len-2; i>=0; --i){
            int min = len;
            far = Math.min(i+A[i], len-1);
            for(int j=i+1; j<=far; ++j){
                if(steps[j] == -1)
                    continue;
                else
                    min = Math.min(min, steps[j]);
            }
            if(min == len)
                steps[i] = -1;
            else
                steps[i]= min+1;
        }
        
        if(steps[0] == -1){
        	System.out.println("No way!");
        	return -1;
        }
        int temp = len;
        for(int i=0; i<len; ++i){
        	if(steps[i] < temp){
        		System.out.print(i+" ");
        		temp = steps[i];
        	}
        }
        
        return steps[0];
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {2,3,1,1,4};
		jump(a);
	}

}

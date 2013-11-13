package recursion;

public class PerSeq {
	static int factorial(int n){
        if(n == 0)
            return 1;
        if(n==1)
            return 1;
        else
            return n * factorial(n-1);
    }
    
    static void permutate(boolean[] seq, int factorial, int n, int k, String re){
        if(k == 0){
            int i = 0;
            while(i < n){
                if(seq[i] == false){
                    re.concat(String.valueOf(++i));
                }
                else{
                    i++;
                    n++;
                }
            }
        }
        else{
            factorial /= n;
            int pos = k / factorial;
            
            for(int j=0; j < pos + 1; j++){
                if(seq[j] == true){
                    pos++;
                }
                
                if(j == pos){
                    seq[j] = true;
                    re.concat(String.valueOf(j+1));
                }
            }
            
            k = k % factorial;
            permutate(seq, factorial, n--, k, re);
        }
    }
    
    public static String getPermutation(int n, int k) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        boolean[] seq = new boolean[n];
        for(int i=0; i<n; ++i)
            seq[i] = false;
        String[] result = {new String("")};
        int factorial = factorial(n);
        
        permutate(seq, factorial, n, k -1, result[0]);
        
        return result[0];
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String a = getPermutation(1, 1);
	}

}

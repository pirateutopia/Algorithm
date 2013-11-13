package amazon;

public class GetMatrix {
	static void getMatrix(int n, int[] p, int[] q){
		int diff = n;
		int m = n + 3;
		
		for(; n<m; ++n){
			int min = -1;
			int max = n;
			
			for(int i=1; i<n; ++i){
				int remainder = n%i;
				int quotient = n/i;
				
				if(remainder == 0){
					if(i >= max){
						break;
					}
					else if(i > min){
						min = i;
						max = quotient;
					}
				}
			}
			//System.out.println(n, max, min);
			if(max-min < diff){
				diff = max - min;
				p[0] = min;
				q[0] = max;
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] p = {0}; int[] q = {0};
		getMatrix(36, p, q);
		System.out.println(p[0]);
		System.out.println(q[0]);
	}

}

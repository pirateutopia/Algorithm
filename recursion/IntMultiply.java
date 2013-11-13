package recursion;

public class IntMultiply {
	public static int multiply(int[] re, int[] a, int[] b){
		if(b[0] == 0)
			return re[0];
		if((b[0] & 1) != 0)
			re[0] += a[0];
		a[0] <<= 1;
		b[0] >>= 1;
		multiply(re, a, b);
		return re[0];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] re = {0};
		int[] a = {5};
		int[] b = {6};
		System.out.println(multiply(re, a, b));
	}

}

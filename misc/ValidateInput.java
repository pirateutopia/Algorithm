package misc;

public class ValidateInput {

	static boolean checkString(String s){
		int len = s.length();
		boolean beginIsZero = false;
		boolean firstChar = true;
		if(len == 0)
			return true;
		else{
			for(int i=0; i<len; ++i){
				if(firstChar){
					if(s.charAt(i) == '0'){
						beginIsZero = true;
					}
					firstChar = false;
				}
				else if(beginIsZero)
					return false;
				if(s.charAt(i)<'0' || s.charAt(i)>'9')
					return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(checkString("10"))
			System.out.println("Yes!");
		else
			System.out.println("The format of input file is invalid!");
	}

}

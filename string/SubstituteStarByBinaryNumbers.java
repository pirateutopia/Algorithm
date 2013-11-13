package string;

import java.util.ArrayList;

public class SubstituteStarByBinaryNumbers {

	public static void main(String[] args) {
		String str = "aa*b*c*";
		int star = 0;
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i)=='*') {
				star++;
			}
		}
		ArrayList<String> result = new ArrayList<String>();
		char[] s = new char[star];
		con(s,star,result,0,str);
		System.out.println(result);
//		ArrayList<String> result = helper(star);
//		System.out.println(result);
	}

	
//	public static ArrayList<String> helper(int star) {
//		ArrayList<String> result = new ArrayList<String>();
//		int n = (int) Math.pow(2, star);
//		for(int i=0; i<n;i++) {
//			String s = Integer.toBinaryString(i);
//			while(s.length()<star){
//				s = "0" + s;
//			}
//			result.add(s);
//		}
//		return result;
//	}
	
	public static void con(char[] s, int star, ArrayList<String> str, int count, String tpl) {
		if(star == 0){
			int r = 0;
			String temp = tpl;
			char[] ch = temp.toCharArray();
			for(int i=0; i<temp.length(); i++){
				if(temp.charAt(i)=='*') {
					ch[i] = s[r];
					r++;
				}
			}
			str.add(new String(ch));
			return;
		}
		int n = star - 1;
		s[count] = '1';
		con(s,n,str,count+1,tpl);
		s[count] = '0';
		con(s,n,str,count+1,tpl);
	}

}

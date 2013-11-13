package string;

import java.util.HashMap;

public class BoyerMoore {

	static HashMap<Character, Integer> findLastOccurenceFunction(char[] alpha, String P){
		HashMap<Character, Integer> lof = new HashMap<Character, Integer>();
		for(Character c: alpha) lof.put(c, -1);
		
		for(int i=0; i<P.length(); ++i){
			char c = P.charAt(i);
			lof.put(c, i); 
		}
		return lof;
	}
	
	static void findOccurence(HashMap<Character, Integer> lof, String T, String P){
		int m = P.length(); int n = T.length();
		int i = m-1, j = m-1;
		int count = 0;
		while(i < n){
			++count;
			if(T.charAt(i) == P.charAt(j)){
				if(j == 0){
					System.out.println("Matching at " + i);
					//break;
					i = i+m;
					j = m-1;
				}
				else{
					--i; --j;
				}
			}
			else{
				int L = 1 + lof.get(T.charAt(i));
				if(j<L)
					i = i+m-j;
				else
					i = i+m-L;
				j = m-1;
			}
		}
		System.out.println(count);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String T = "ATCGACTGAGCTCTAGCG";
		String P = "CTC";
		char[] alpha = {'A', 'C', 'G', 'T'};
		
		HashMap<Character, Integer> alphabet = findLastOccurenceFunction(alpha, P);
		findOccurence(alphabet, T, P);
	}

}

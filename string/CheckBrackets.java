package string;

import java.util.Stack;

public class CheckBrackets {
	
	static boolean checkParenthesis(String s){
		Stack<Character> sk = new Stack<Character>();
		Character c;
		Character top;
		for(int i=0; i<s.length(); ++i){
			c = s.charAt(i);
			if(c=='[' || c=='(' || c=='{')
				sk.push(c);
			else if(c==']' || c==')' || c=='}'){
				if(sk.empty())
					return false;
				else{
					top = sk.pop();
					if((top=='[' && c==']') ||
						(top=='(' && c==')') ||
						(top=='{' && c=='}'))
						continue;
					else
						return false;
				}
			}
		}
		if(sk.empty())
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "[](){a";
		boolean result = checkParenthesis(s);
		if(result)
			System.out.println("Yes");
		else
			System.out.println("NO");
	}

}

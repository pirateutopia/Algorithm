package recursion;

import java.util.ArrayList;
import java.util.List;

public class Permutation {
	public static ArrayList<ArrayList<Integer>> permutate(ArrayList<Integer> numList){
        if(numList == null)
            return null;
        ArrayList<ArrayList<Integer>> longList = new ArrayList<ArrayList<Integer>>();
        
        if(numList.size() == 1){
            longList.add(numList);
            return longList;
        }
        
        ArrayList<Integer> remainder = new ArrayList<Integer>();
        for(int i=1; i<numList.size(); ++i)
        	remainder.add(numList.get(i));
        ArrayList<ArrayList<Integer>> shortList = permutate(remainder);
        int b = 10;
        Integer firstNum = numList.get(0);
        int a = firstNum.intValue();
        List<Integer> newList;
        
        for(List<Integer> single : shortList){
            for(int i=0; i<=single.size(); ++i){
                newList = new ArrayList<Integer>();
                for(int j=0; j<i; ++j)
                    newList.add(single.get(j));
                newList.add(firstNum);
                for(int j=i; j<single.size(); ++j)
                    newList.add(single.get(j));
            }
        }
        
        return longList;
    }
    
    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        ArrayList<Integer> num1 = new ArrayList<Integer>();
        for(int i=0; i<num.length; ++i)
            num1.add(new Integer(num[i]));
        return permutate(num1);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = {0, 1};
		permute(num);
	}

}

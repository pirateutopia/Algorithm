package cs6364;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class TreeNode{
	public ArrayList<Integer> state;
	public int h;
	public int pos;
	public ArrayList<Integer> actions;
	public ArrayList<Integer> scores;
	public TreeNode child;
	
	public int[][] table1 = {{0,1,2,1,2,3,2,3,4},{1,0,1,2,1,2,3,2,3},{2,1,0,3,2,1,4,3,2},{3,2,1,2,1,0,3,2,1},
			{4,3,2,3,2,1,2,1,0}	,{3,2,3,2,1,2,1,0,1},{2,3,4,1,2,3,0,1,2},{1,2,3,0,1,2,1,2,3}};
	public int[][] table2 = {{3,4},{2,3,4},{2,4},{1,3,4},{1,2,3,4},{1,2,4},{1,3},{1,2,3},{1,2}};
	public int[] table3 = {2,3,2,3,4,3,2,3,2};

	TreeNode(ArrayList<Integer> s, int action){
		state = new ArrayList<Integer>();
		actions = new ArrayList<Integer>();
		scores = new ArrayList<Integer>();
		for(int i=0; i<s.size(); ++i)
			state.add(s.get(i));
		int index = 0;
		for(; index<9; ++index)
			if(state.get(index) == 0)
				break;
		changeState(state, action, index);
		h = setH(state);

		for(pos=0; pos<9; ++pos)
			if(state.get(pos) == 0)
				break;

		ArrayList<Integer> potential;
		int score = 0;
		int ac = 0;
		for(int i=0; i<table3[pos]; ++i){
			potential = new ArrayList<Integer>();
			ac = table2[pos][i];
			actions.add(ac);
			for(int j=0; j<9; ++j){
				potential.add(state.get(j));
			}
			changeState(potential, ac, pos);
			score = setH(potential);
			scores.add(score);
		}
		
	}
	
	int setH(ArrayList<Integer> st){
		int hVal = 0;
		for(int i=0; i<9; ++i)
			if(st.get(i) != 0){
	    		hVal += table1[st.get(i)-1][i];
			}
		return hVal;
	}
	
	int getAction(){
		int min = 100;
		int idx = 0;
		int act = 0;
		for(int i=0; i<table3[pos]; ++i){
			if(scores.get(i) < min){
				min = scores.get(i);
				idx = i;
				act = table2[pos][i];
			}
		}
		scores.set(idx, 100);
		return act;
	}
	
	void changeState(ArrayList<Integer> state, int action, int pos){
		if(action != 0){
			if(pos == 0){
				if(action == 3){
					state.set(0, state.get(1));
					state.set(1, 0);
				}
				else if(action == 4){
					state.set(0, state.get(3));
					state.set(3, 0);
				}
			}
			else if(pos == 1){
				if(action == 2){
					state.set(1, state.get(0));
					state.set(0, 0);
				}
				else if(action == 3){
					state.set(1, state.get(2));
					state.set(2, 0);
				}
				else if(action == 4){
					state.set(1, state.get(4));
					state.set(4, 0);
				}
			}
			else if(pos == 2){
				if(action == 2){
					state.set(2, state.get(1));
					state.set(1, 0);
				}
				else if(action == 4){
					state.set(2, state.get(5));
					state.set(5, 0);
				}
			}
			else if(pos == 3){
				if(action == 1){
					state.set(3, state.get(0));
					state.set(0, 0);
				}
				else if(action == 3){
					state.set(3, state.get(4));
					state.set(4, 0);
				}
				else if(action == 4){
					state.set(3, state.get(6));
					state.set(6, 0);
				}
			}
			else if(pos == 4){
				if(action == 1){
					state.set(4, state.get(1));
					state.set(1, 0);
				}
				else if(action == 2){
					state.set(4, state.get(3));
					state.set(3, 0);
				}
				else if(action == 3){
					state.set(4, state.get(5));
					state.set(5, 0);
				}
				else if(action == 4){
					state.set(4, state.get(7));
					state.set(7, 0);
				}
			}
			else if(pos == 5){
				if(action == 1){
					state.set(5, state.get(2));
					state.set(2, 0);
				}
				else if(action == 2){
					state.set(5, state.get(4));
					state.set(4, 0);
				}
				else if(action == 4){
					state.set(5, state.get(8));
					state.set(8, 0);
				}
			}
			else if(pos == 6){
				if(action == 1){
					state.set(6, state.get(3));
					state.set(3, 0);
				}
				else if(action == 3){
					state.set(6, state.get(7));
					state.set(7, 0);
				}
			}
			else if(pos == 7){
				if(action == 1){
					state.set(7, state.get(4));
					state.set(4, 0);
				}
				else if(action == 3){
					state.set(7, state.get(6));
					state.set(6, 0);
				}
				else if(action == 4){
					state.set(7, state.get(8));
					state.set(8, 0);
				}
			}
			else if(pos == 8){
				if(action == 1){
					state.set(8, state.get(5));
					state.set(5, 0);
				}
				else if(action == 2){
					state.set(8, state.get(7));
					state.set(7, 0);
				}
			}
		}
	}
}

public class EightPuzzle {
	public int count = 0;

	public static int createTree(TreeNode root, int action, int depth){
		if(root.h == 0)
			return 0;
		else if (depth == 30)
			return -1;
		else{
			TreeNode child = null;
			child = new TreeNode(root.state, action);
			root.child = child;

			int result = createTree(child, child.getAction(), ++depth);
			if(result == 0)
				return 0;
			else if(root.getAction() == 0)
				return -1;
			else
				return createTree(child, child.getAction(), depth);
		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ArrayList<Integer> a;
		TreeNode root;
		FileReader reader = new FileReader("input.txt");
		BufferedReader br = new BufferedReader(reader);
		FileWriter fw = new FileWriter("output.txt");
		String s = null;
		String str = "";
		int count = 0;
		while((s = br.readLine()) != null) {
			count++;
			a = new ArrayList<Integer>();
			String[] ss = new String[20];
			ss = s.split(","); 
			for (int i = 0; i < ss.length; i++) 
				a.add(Integer.parseInt(ss[i]));
				
			root = new TreeNode(a, 0);
			int result = createTree(root, root.getAction(), 0);
			str += "\n";
			str += "Result of position ";
			str += count;
			str += ":\n";
			if(result == 0){
				while(root != null){
					for(int i=0; i<8; ++i){
						str += root.state.get(i).toString();
						str += ", ";
					}
					str += root.state.get(8).toString();
					str += "\n";
					root = root.child;
				}
			}
			else{
				str += "No solution!\n";
			}

		}
		fw.write(str); 
		br.close();
		fw.close();
		reader.close();
	}

}

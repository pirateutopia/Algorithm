package bst;

import java.util.ArrayList;
import java.util.Stack;

class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int x) { val = x;}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) { val = x; next = null; }
	
}

public class ListToBST {
	public static TreeNode createBST(ListNode[] head, int start, int end){
		//NOTE:when start = end, goes to next level, now start > end is true. so there is one recursive.
		if(start > end ) return null;
		int mid = (end + start) / 2;//avoid overflow
		TreeNode left = createBST(head, start, mid -1);
		
		TreeNode parrent = new TreeNode(head[0].val);
		parrent.left = left;
		if(head[0].next != null){
			head[0] = head[0].next;
		}
		parrent.right = createBST(head, mid + 1, end);
		return parrent;
	}

	public static TreeNode createBST1(TreeNode[] head, int start, int end){
		//NOTE:when start = end, goes to next level, now start > end is true. so there is one recursive.
		if(start > end ) return null;
		int mid = (end + start) / 2;//avoid overflow
		TreeNode left = createBST1(head, start, mid -1);
		
		TreeNode parrent = head[0];
		parrent.left = left;
		if(head[0].right != null){
			head[0] = head[0].right;
		}
		parrent.right = createBST1(head, mid + 1, end);
		return parrent;
	}
	
	public static void printResult(TreeNode root){
		if(root != null){
			System.out.println(root.val);
			printResult(root.left);
			printResult(root.right);
		}
	}

	public static void printTree(TreeNode root){
		if(root != null){
			printTree(root.left);
			System.out.println(root.val);
			printTree(root.right);
		}
		return;
	}
	
	public static void myIterInorder(TreeNode root){
		if(root == null){
			System.out.println("Empty!");
			return;
		}
		Stack<TreeNode> sk = new Stack<TreeNode>();
		sk.push(root);
		TreeNode top;
		while(!sk.empty()){
			top = sk.peek();
			if(top.left != null){
				sk.push(top.left);
			}
			else{
				top = sk.pop();
				System.out.println(top.val);
				if(!sk.empty()){
					top = sk.pop();
					System.out.println(top.val);
					if(top.right != null)
						sk.push(top.right);
				}
				else{
					if(top.right != null)
						sk.push(top.right);
				}
			}
		}
	}

	public static void iterInorder(TreeNode root){
		Stack<TreeNode> sk = new Stack<TreeNode>();
		TreeNode current = root;
		while(!sk.empty() || current!=null){
			if(current != null){
				sk.push(current);
				current = current.left;
			}	
			else{
				current = sk.pop();
				System.out.println(current.val);
				current = current.right;
			}
		}
	}
	
	public static TreeNode mergeTreeList(TreeNode root1, TreeNode root2){
		TreeNode head;
		TreeNode prev;
		int runner;
		if(root1.val <= root2.val){
			head = root1;
			prev = root1;
			root1 = root1.right;
			runner = 1;
		}
		else{
			head = root2;
			prev = root2;
			root2 = root2.right;
			runner = 2;
		}

		while(root1!=null && root2!=null){
			if(root1.val <= root2.val && runner == 1){
				prev = root1;
				root1 = root1.right;
			}
			else if(root1.val > root2.val && runner == 2){
				prev = root2;
				root2 = root2.right;
			}
			else if(root1.val <= root2.val && runner == 2){
				prev.right = root1;
				prev = root1;
				root1 = root1.right;
				runner = 1;
			}
			else{
				prev.right = root2;
				prev = root2;
				root2 = root2.right;
				runner = 2;
			}
		}
		
		if(root1 == null)
			prev.right = root2;
		else
			prev.right = root1;
		return head;
	}
	
	public static void showTreeList(TreeNode root){
		while(root != null){
			System.out.println(root.val);
			root = root.right;
		}
	}
	
	public static TreeNode mergeBST(TreeNode root1, TreeNode root2){
		TreeNode r1 = flattenBST(root1, 0);
		TreeNode r2 = flattenBST(root2, 0);
		//showTreeList(r1);
		//showTreeList(r2);
		TreeNode listHead = mergeTreeList(r1, r2);
		//showTreeList(listHead);

		TreeNode h = listHead;
		int count = 0;
		while(h != null){
			h = h.right;
			count++;
		}
		TreeNode[] header = {listHead};
		//System.out.println(count);
		TreeNode root = null;
		if(header[0] == null)
			System.out.println("null");
		else
		 root = createBST1(header, 1, count);
		return root;
	}

	public static TreeNode myFlattenBST(TreeNode root){
		Stack<TreeNode> sk = new Stack<TreeNode>();
		TreeNode current = root;
		TreeNode temp;
		ArrayList<TreeNode> result = new ArrayList<TreeNode>();
		while(!sk.empty() || current!=null){
			if(current != null){
				sk.push(current);
				current = current.left;
			}	
			else{
				current = sk.pop();
				temp = current;
				current = current.right;
				if(current != null)
					temp.right = current;
				else if(!sk.empty())
					temp.right = sk.peek();
				temp.left = null;
				result.add(temp);
			}
		}
		return result.get(0);
	}

	public static TreeNode flattenBST(TreeNode root, int flag){
		if(root == null)
			return null;
		TreeNode l; TreeNode r;
		l = flattenBST(root.left, 1);
		r = flattenBST(root.right, 2);
		root.left = l;
		root.right = r;
		if(l != null)
			l.right = root;
		if(r != null)
			r.left = root;
		if(flag == 1 && root.right != null)
			return root.right;
		if(flag == 2 && root.left != null)
			return root.left;
		if(flag == 0)
			while(root.left != null)
				root = root.left;
		return root;
	}
	
	public static boolean checkBST(TreeNode root, int min, int max){
		if(root == null)
			return true;
		if(root.val>max || root.val<min)
			return false;
		else
			return(checkBST(root.left, min, root.val) && checkBST(root.right, root.val, max));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListNode prev1 = new ListNode(2);
		ListNode head1 = prev1;
		ListNode cur1;
		int count1 = 1;
		for(int i=3; i<10; ++i){
			cur1 = new ListNode(i);
			prev1.next = cur1;
			prev1 = cur1;
			count1++;
		}
		ListNode[] header1 = {head1};
		TreeNode root1 = createBST(header1, 1, count1);

		ListNode prev2 = new ListNode(1);
		ListNode head2 = prev2;
		ListNode cur2;
		int count2 = 1;
		for(int i=4; i<10; ++i){
			cur2 = new ListNode(i);
			prev2.next = cur2;
			prev2 = cur2;
			count2++;
		}
		ListNode[] header2 = {head2};
		TreeNode root2 = createBST(header2, 1, count2);
		
		iterInorder(mergeBST(root1, root2));
		/*if(checkBST(root, -Integer.MAX_VALUE, Integer.MAX_VALUE))
			System.out.println("Yes");
		else
			System.out.println("No");*/
	}

}

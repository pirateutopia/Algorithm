package bst;

import java.util.Stack;

/*class TreeNode{
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int x) { val = x;}
}*/

class Result{
	public Result(TreeNode s, TreeNode e) {
		// TODO Auto-generated constructor stub
		start = s;
		end = e;
	}
	TreeNode start;
	TreeNode end;
}

public class MergeBST {
	
	public static Result flattenBST(TreeNode root){
		if(root == null){
			return null;
		}
		
		Result left = flattenBST(root.left);
		Result right = flattenBST(root.right);
		TreeNode start; TreeNode end;
		if(left != null){
			root.left = left.end;
			left.end.right = root;
			start = left.start;
		}else{
			start = root;
		}
		if(right != null){
			root.right = right.start;
			right.start.left = root;
			end = right.end;
		}else{
			end = root;
		}
		return new Result(start, end);
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
	
	public static TreeNode createBST(TreeNode[] head, int start, int end){
		//NOTE:when start = end, goes to next level, now start > end is true. so there is one recursive.
		if(start > end ) return null;
		int mid = (end + start) / 2;//avoid overflow
		TreeNode left = createBST(head, start, mid -1);
		
		TreeNode parent = head[0];
		parent.left = left;
		if(head[0].right != null){
			head[0] = head[0].right;
		}
		parent.right = createBST(head, mid + 1, end);
		return parent;
	}
	
	public static TreeNode mergeBST(TreeNode root1, TreeNode root2){
		TreeNode r1 = flattenBST(root1).start;
		TreeNode r2 = flattenBST(root2).start;
		TreeNode listHead = mergeTreeList(r1, r2);

		TreeNode h = listHead;
		int count = 0;
		while(h != null){
			h = h.right;
			count++;
		}
		TreeNode[] header = {listHead};
		TreeNode root = null;
		if(header[0] == null)
			System.out.println("null");
		else
			root = createBST(header, 1, count);
		return root;
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root1 = new TreeNode(5);
		TreeNode a = new TreeNode(2);
		TreeNode b = new TreeNode(11);
		root1.left = a;
		root1.right = b;
		TreeNode c = new TreeNode(1);
		TreeNode d = new TreeNode(3);
		a.left = c;
		a.right = d;
		TreeNode j = new TreeNode(4);
		d.right = j;
		TreeNode e = new TreeNode(6);
		b.left = e;
		TreeNode k = new TreeNode(7);
		e.right = k;
		TreeNode l = new TreeNode(8);
		k.right = l;
		TreeNode m = new TreeNode(9);
		l.right = m;
		
		///////////////////////////////////////////
		TreeNode root2 = new TreeNode(5);
		TreeNode f = new TreeNode(4);
		TreeNode g = new TreeNode(9);
		root2.left = f;
		root2.right = g;
		TreeNode h = new TreeNode(-1);
		f.left = h;
		TreeNode i = new TreeNode(10);
		g.right = i;
		
		/*
		 * Hard coding two bst.
		 * Tree1:
		 *                 5
		 *        2                 11 
		 *    1        3        6
		 *                4       7
		 *                          8
		 *                            9     
		 * Tree2:
		 *                 5
		 *        4                  9
		 *    -1                         10
		 *    The program print the value of nodes of new tree by inorder traversal
		 *    which is -1 1 2 3 4 5 5 6 8 9 10.
		 *    By the way,  flattenBST method can convert a bst into a doubly linked list
		 *    in O(n) time (without extra space???????).
		 */
		
		iterInorder(mergeBST(root1, root2));
	}

}

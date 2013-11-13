package bst;

import java.util.Arrays;

public class ConstructBST {

	static public TreeNode buildTree(int[] preorder, int[] inorder) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
        if(inorder.length == 0)
            return null;
        int value = preorder[0];
        TreeNode root = new TreeNode(value);
        int pos = 0;
        while(inorder[pos] != value){
            pos++;
        }
        
        TreeNode left;
        TreeNode right;
        int[] emp = {};
        if(pos == 0)
            left = buildTree(emp, emp);
        else
            left = buildTree(Arrays.copyOfRange(preorder, 1, pos+1), Arrays.copyOfRange(inorder, 0, pos));
            
        if(pos == inorder.length-1)
            right = buildTree(emp, emp);
        else
            right = buildTree(Arrays.copyOfRange(preorder, pos+1, preorder.length), Arrays.copyOfRange(inorder, pos+1, inorder.length));
            
        root.left = left;
        root.right = right;
        return root;
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a1 = {1,2,3}; int[] a2 = {2,1,3};
		TreeNode a = buildTree(a1, a2);
	}

}

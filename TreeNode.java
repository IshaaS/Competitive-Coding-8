// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/description/

// Time Complexity :O(n)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// We can flatten a binary tree to a linked list using preorder traversal.
// In naive solutions, we store the preorder traversal in an auxiliary list, and then reassign the left and right pointers accordingly.

// I initially solved it recursively and iteratively using a stack (both take O(N) extra space), but we can go further.

// Instead of using extra space to store the traversal, we can modify the tree in-place using Morris Traversal-like logic, where we rearrange the pointers on the fly, achieving O(1) space.

 // Definition for a binary tree node.
 public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

 //dfs preorder using recursion
// class Solution {
//     List<TreeNode> preOrder;
//     public void flatten(TreeNode root) {
//         this.preOrder=new ArrayList<>();
//         dfs(root);
//         for(int i=1;i<preOrder.size();i++){
//             root.left=null;
//             root.right=preOrder.get(i);
//             root=root.right;
//         }
//     }
//     private void dfs(TreeNode root){
//         //base
//         if(root==null) return;
//         preOrder.add(root);
//         dfs(root.left);
//         dfs(root.right);
//     }
// }

//dfs preorder using stack
// class Solution {
//     public void flatten(TreeNode root) {
//         if(root==null) return;
//         List<TreeNode> preOrder=new ArrayList<>();
//         Stack<TreeNode> st = new Stack<>();
//         st.add(root);
//         while(!st.isEmpty()){
//             TreeNode current =st.pop();
//             preOrder.add(current);
//             if(current.right!=null)st.add(current.right);
//             if(current.left!=null)st.add(current.left);
//         }
//         for(int i=1;i<preOrder.size();i++){
//             root.left=null;
//             root.right=preOrder.get(i);
//             root=root.right;
//         }
//     }
// }

//o(1) space
class Solution {
    public void flatten(TreeNode root) {
        TreeNode current = root;
        while(current!=null){
            if(current.left==null) current=current.right;
            else{
                TreeNode prev = current;
                current=current.left;
                while(current.right!=null) current=current.right;
                if(current.right==null) {
                    current.right=prev.right;
                    prev.right=prev.left;
                    prev.left=null;
                }
                current=prev.right;
            }
        }
    }
}

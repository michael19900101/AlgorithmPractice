package algorithm;

import java.util.ArrayList;

/**
 * 算法：恢复二叉搜索树
 *
 * 思路：
 * 二叉搜索树的中序遍历就是一个递增的序列，所以可以根据这个序列是否递增来判断其是否是二叉搜索树；
 * 1.通过中序遍历，将遍历结果放在一个ArrayList中；
 * 2.双指针，从头尾向中间靠；
 */
public class RecoverTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public void recoverTree(TreeNode root) {
        ArrayList<TreeNode> path = new ArrayList<TreeNode>();
        //中序遍历，获得这个二叉搜索树的遍历序列
        dfs(root, path);

        int start = 0, end = path.size() - 1;
        while (start < end) {   //注意是<
            if (path.get(start).val < path.get(start + 1).val) {
                start++;
            } else if (path.get(end).val > path.get(end - 1).val) {
                end--;
            } else {
                //找到错误的节点，交换回来，只交换val
                int tmp = path.get(end).val;
                path.get(end).val = path.get(start).val;
                path.get(start).val = tmp;
                return;
            }
        }

    }

    //中序遍历，获得这个二叉搜索树的遍历序列
    private void dfs(TreeNode node, ArrayList<TreeNode> path) {
        if (node == null) {
            return;
        }
        dfs(node.left, path);
        path.add(node);        //将该节点加入path
        dfs(node.right, path);
    }

    public static void main(String[] args) {
        RecoverTree morrisTraversal = new RecoverTree();
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        morrisTraversal.recoverTree(root);
    }
}

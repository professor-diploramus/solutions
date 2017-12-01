package org.pdiploramus;

/**
 *
 */
public class General {

    int sum;

    void dfs(Utils.TreeNode n, boolean add) {
        if (n == null) return;
        if (add) {
            sum += n.s;
        }
        dfs(n.left, !add);
        dfs(n.right, !add);
    }


    public static void main(String[] args) {
        Utils.TreeNode a = new Utils.TreeNode(3);
        Utils.TreeNode b = new Utils.TreeNode(4);
        Utils.TreeNode c = new Utils.TreeNode(5);
        Utils.TreeNode d = new Utils.TreeNode(1);
        Utils.TreeNode e = new Utils.TreeNode(3);
        Utils.TreeNode f = new Utils.TreeNode(1);

        a.left = b;
        a.right = c;
        b.left = d;
        b.right = e;
        c.right = f;

        General g = new General();
        g.dfs(a, true);
        System.out.println(g.sum);
    }
}

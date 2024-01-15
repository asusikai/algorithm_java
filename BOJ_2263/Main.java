import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int postIndex;

    static class TreeNode {
        int data;
        TreeNode left, right;

        public TreeNode(int item) {
            data = item;
            left = right = null;
        }
    }

    static TreeNode buildTree(int[] inorder, int[] postorder, int inStart, int inEnd, HashMap<Integer, Integer> map) {
        if (inStart > inEnd)
            return null;

        int rootValue = postorder[postIndex--];
        TreeNode root = new TreeNode(rootValue);

        int inIndex = map.get(rootValue);

        root.right = buildTree(inorder, postorder, inIndex + 1, inEnd, map);
        root.left = buildTree(inorder, postorder, inStart, inIndex - 1, map);

        return root;
    }

    static TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);

        return buildTree(inorder, postorder, 0, inorder.length - 1, map);
    }

    static void printPreorder(TreeNode node) {
        if (node == null)
            return;

        System.out.print(node.data + " ");
        printPreorder(node.left);
        printPreorder(node.right);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] inorder = new int[N];
        int[] postorder = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i<N; i++){
            inorder[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine()," ");
        for(int i = 0; i<N; i++){
            postorder[i] = Integer.parseInt(st.nextToken());
        }

        TreeNode root = buildTree(inorder, postorder);

        printPreorder(root);

    }
}

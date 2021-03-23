import java.util.Stack;

/*
 * @desciption 定义一个泛型二叉树节点
 * @author xianchun
 * @date  12:36 2021/3/23
 */
public class TreeNode<T extends Comparable<T>> {
    /*节点值*/
    private T data;
    /*节点左子树*/
    private TreeNode left;
    /*节点右子树*/
    private TreeNode right;

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }


    /**
     * @return void
     * @desciption 采用非递归的方式中序遍历该二叉树
     * @author xianchun
     * @date 12:40 2021/3/23
     */
    public void midTravel(TreeNode treeNode) {
        //非递归实现，就相当于一个左根右的顺序入栈的操作
        Stack<TreeNode> treeNodes = new Stack<>();
        while(treeNode!=null||!treeNodes.isEmpty()){
           while(treeNode!=null){
               treeNodes.push(treeNode);
               treeNode=treeNode.getLeft();
           }
           if(!treeNodes.isEmpty()){
               treeNode = treeNodes.pop();
               System.out.println(treeNode.getData());
               treeNode=treeNode.getRight();
           }
        }


    }

    /**
     * 采用递归的方式中序遍历二叉树
     * 中序遍历：左根右
     * 前序遍历：根左右
     * 后序遍历：左右根
     */
    public void midTravelByRecursion(TreeNode treeNode) {
        if (treeNode != null) {
            midTravelByRecursion(treeNode.getLeft());
            System.out.println(treeNode.getData());
            midTravelByRecursion(treeNode.getRight());
        }
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) {
        TreeNode<Integer> treeNode = new TreeNode<>(10);
        treeNode.setLeft(new TreeNode(5));
        TreeNode<Integer> rightTreeNode = new TreeNode<>(15);
        treeNode.setRight(rightTreeNode);
        rightTreeNode.setLeft(new TreeNode(6));
        rightTreeNode.setRight(new TreeNode(2));

        treeNode.midTravelByRecursion(treeNode);
        treeNode.midTravel(treeNode);
    }
}
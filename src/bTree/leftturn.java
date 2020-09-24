package bTree;



class RBTree<T extends Comparable<T>> {

    private RBTNode<T> mRoot;    // 根结点

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    public class RBTNode<T extends Comparable<T>> {
        boolean color;        // 颜色
        T key;                // 关键字(键值)
        RBTNode<T> left;    // 左孩子
        RBTNode<T> right;    // 右孩子
        RBTNode<T> parent;    // 父结点

        public RBTNode(T key, boolean color, RBTNode<T> parent, RBTNode<T> left, RBTNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

    }


        private void leftRotate(RBTNode<T> x){
            RBTNode<T> y=x.right;
            x.right=y.left;
            if(y.left!=null){
                y.left.parent=x;
            }

                y.parent=x.parent;
            if(x.parent==null){
                this.mRoot=y;
            }else{
                if(x.parent.left==x){
                    x.parent.left=y;
                }
                else
                    x.parent.right=y;
            }

            y.left=x;
            x.parent=y;

        }

    private void rightRotate(RBTNode<T> x){
        RBTNode<T> y=x.left;
        x.left=y.right;
        if(y.right!=null)
            y.right.parent=x;
        y.parent=x.parent;
        if(x.parent==null){
            this.mRoot=y;
        }
        else{
            if(x.parent.left==x){
                x.parent.left=y;
            }
            else
                x.parent.right=y;
        }

        y.right=x;
        x.parent=y;
    }

}
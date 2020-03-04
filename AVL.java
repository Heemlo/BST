public class AVL {
    public class Node {
        private int value;
        private Node parent;
        private Node left;
        private Node right;
        private int height=0;
        private Node(int v,Node p) {
            value=v;
            parent=p;
        }
        public int getValue() {
            return value;
        }
        private void delete() {
            Node toDelete = this;
            while(true) {
                if(toDelete.right == null && toDelete.left == null) { //no children, delete it and return
                    if(toDelete.parent==null) {
                        root = null;
                        return;
                    }
                    if (toDelete.value > toDelete.parent.value)
                        toDelete.parent.right = null;
                    else
                        toDelete.parent.left = null;
                    balance(toDelete.parent);
                    return;
                }
                if(toDelete.right != null && toDelete.left != null) { //two children, set toDelete value equal to successor, then set successor as next Node toDelete
                    Node nextBiggest=toDelete.findNextIter();
                    toDelete.value=nextBiggest.value;
                    toDelete=nextBiggest;
                    continue;
                }
                Node child;                                         //one child, set toDelete value equal to child, then set child as next Node toDelete
                if(toDelete.left!=null)
                    child=toDelete.left;
                else
                    child=toDelete.right;
                toDelete.value=child.value;
                toDelete=child;
            }
        }
        /**
         *
         * @return successor Node of this
         */
        public Node findNextIter() {
            if(right!=null) {                //right subtree exists
                return right.findMinIter();
            }
            Node curr=this;                  //right subtree does not exist, move up the tree until finding a node that is a left child
            while(curr.parent!=null&&curr.parent.left!=curr)
                curr=curr.parent;
            return curr.parent;
        }
        /**
         *
         * @return previous Node of this
         */
        public Node findPrevIter() {
            if(left!=null) {                //left subtree exists
                return left.findMaxIter();
            }
            Node curr=this;                  //left subtree does not exist, move up the tree until finding a node that is a right child
            while(curr.parent!=null&&curr.parent.right!=curr)
                curr=curr.parent;
            return curr.parent;
        }
        public Node findMinIter() {
            Node smallest=this;
            while(smallest.left!=null) smallest=smallest.left;
            return smallest;
        }
        public Node findMaxIter() {
            Node biggest=this;
            while(biggest.right!=null) biggest=biggest.right;
            return biggest;
        }

        /**
         * updates the height field of this to be 1 more than the height of its higher child
         */
        private void updateHeight() {
            int leftH=-1;
            int rightH=-1;
            if(left!=null)
                leftH=left.height;
            if(right!=null)
                rightH=right.height;
            height=Math.max(leftH,rightH)+1;
        }
        /**
         * performs a right rotation on this node
         */
        private void rightRotate() {
            Node tmpLeft=left;
            Node tmpParent=parent;
            if(left.right!=null)
                left.right.parent=this;
            left=left.right;
            tmpLeft.right=this;
            parent=tmpLeft;
            if(tmpParent==null) {
                tmpLeft.parent=null;
                root=tmpLeft;
            }
            else {
                tmpLeft.parent = tmpParent;
                if(tmpParent.left==this)
                    tmpParent.left=tmpLeft;
                else
                    tmpParent.right=tmpLeft;
            }
            tmpLeft.updateHeight();
            this.updateHeight();
        }
        /**
         * performs a left rotation on this node
         */
        private void leftRotate() {
            Node tmpRight=right;
            Node tmpParent=parent;
            if(right.left!=null)
                right.left.parent=this;
            right=right.left;
            tmpRight.left=this;
            parent=tmpRight;
            if(tmpParent==null) {
                tmpRight.parent=null;
                root=tmpRight;
            }
            else {
                tmpRight.parent = tmpParent;
                if(tmpParent.left==this)
                    tmpParent.left=tmpRight;
                else
                    tmpParent.right=tmpRight;
            }
            tmpRight.updateHeight();
            this.updateHeight();
        }
    }

    private Node root;
    public AVL() {}
    /**
     * moves up the tree, making sure every node is balanced along the way, starting at node
     * @param node
     */
    private void balance(Node node) {
        while(node!=null) {
            node.updateHeight();
            if(Math.abs(height(node.left)-height(node.right))>1) {
                if (height(node.left) > height(node.right)) {                 //left heavy
                    if (height(node.left.left) > height(node.left.right)) {   //left left imbalance
                        node.rightRotate();
                    } else {                                                //left right imbalance
                        node.left.leftRotate();
                        node.rightRotate();
                    }
                } else {                                                    //right heavy
                    if (height(node.right.right) > height(node.right.left)) { //right right imbalance
                        node.leftRotate();
                    } else {                                                //right left imbalance
                        node.right.rightRotate();
                        node.leftRotate();
                    }
                }
            }
            node=node.parent;
        }
    }
    /**
     *
     * @param node
     * @return height of node or -1 if it's null
     * used to avoid having to check if a child is null
     */
    private static int height(Node node) {
        if(node==null) return -1;
        return node.height;
    }
    public int counter=0;
    public void insertIter(int toInsert) {
        if(root==null) {
            root=new Node(toInsert,null);
            return;
        }
        Node curr=root;
        while((toInsert>curr.value&&curr.right!=null)||(toInsert<curr.value&&curr.left!=null)) {  //move down the tree as far as we can go
            if(toInsert>curr.value) {
                curr=curr.right;
            }
            else curr=curr.left;
            counter++;
        }
        Node in=new Node(toInsert,curr);
        if(toInsert>curr.value) {                                   //we're at the bottom, insert left or right
            curr.right=in;
            return;
        }
        curr.left=in;
        balance(in);
    }
    /**
     *
     * @param toDelete
     * removes the Node with value toDelete
     */
    public void deleteIter(int toDelete) {
        this.findValue(toDelete).delete();
    }
    /**
     *
     * @param value
     * @return successor Node of the Node with value
     */
    public Node findNextIter(int value) {
        return this.findValue(value).findNextIter();
    }
    /**
     *
     * @param value
     * @return previous Node of the Node with value
     */
    public Node findPrevIter(int value) {
        return this.findValue(value).findPrevIter();
    }
    public Node findMinIter() { return root.findMinIter(); }
    public Node findMaxIter() {
        return root.findMaxIter();
    }
    /**
     *
     * @param toFind
     * @return Node with value toFind
     */
    private Node findValue(int toFind) {
        Node curr=root;
        while(curr!=null) {
            if(curr.value==toFind)
                return curr;
            if(toFind>curr.value)
                curr=curr.right;
            else
                curr=curr.left;
        }
        return null;
    }
}

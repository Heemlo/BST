public class BST {
    private BST parent;
    private BST left;
    private BST right;
    private Integer value;
    public BST() {}
    private BST(BST p) {
        parent=p;
    }
    /**
     *
     * @param in value to insert
     */
    public void insertRec(int in) {
        if(value==null) {          //set the value of a node equal to input when it finds null node
            value=in;
            left=new BST(this);    //maintains leaves with null values
            right=new BST(this);
            return;
        }
        if(in>value) {
            right.insertRec(in);      //move right
            return;
        }
        if(in<value) {               //move left
            left.insertRec(in);
        }
    }
    /**
     *
     * @param toDelete value to be deleted from tree
     */
    public void deleteRec(int toDelete) {
        this.findValue(toDelete).deleteRecHelper();
    }
    private void deleteRecHelper() {
        if(this.left.value==null&&this.right.value==null) {    //no children
            this.value=null;
            this.right=this.left=null;
            return;
        }
        if(this.left.value!=null&&this.right.value!=null) {    //2 children
            BST nextBiggest=this.findNextRec();
            this.value=nextBiggest.value;
            nextBiggest.deleteRecHelper();
            return;
        }
        BST child;                                             //1 child
        if(this.left.value!=null) child=left;
        else child=right;
        this.value=child.value;
        child.deleteRecHelper();
    }
    /**
     *
     * @param v
     * @return a BST (which is a subtree of this) with a root which is the successor of the node with value v
     */
    public BST findNextRec(int v) {
        return this.findValue(v).findNextRec();
    }
    /**
     *
     * @param v
     * @return a BST (which is a subtree of this) with value which is the previous of the node with value v
     */
    public BST findPrevRec(int v) {
        return this.findValue(v).findPrevRec();
    }
    /**
     *
     * @return a BST whose root is the successor of the node this function is called on
     */
    public BST findNextRec() {
        if(this.right.value!=null)            //right subtree exists
            return this.right.findMinRec();
        else {                                //right subtree does not exist
            BST next=parent;
            while(next!=null&&next.findPrevRec()!=this)
                next=next.parent;
            return next;
        }
    }
    /**
     *
     * @return a BST whose root is the previous of the node this function is called on
     */
    public BST findPrevRec() {
        if(this.left.value!=null)            //left subtree exists
            return this.left.findMaxRec();
        else {                               //left subtree does not exist
            BST next=parent;
            while(next!=null&&next.findNextRec()!=this)
                next=next.parent;
            return next;
        }
    }
    /**
     *
     * @return a subtree whos root is the minimum node of this
     */
    public BST findMinRec() {
        if(left.value!=null)
            return left.findMinRec();
        else
            return this;
    }
    /**
     *
     * @return a subtree whos root is the maximum node of this
     */
    public BST findMaxRec() {
        if(right.value!=null)
            return right.findMaxRec();
        else
            return this;
    }
    public int getValue() {
        return value;
    }

    /**
     *
     * @param v
     * @return subtree whos root has value v
     */
    public BST findValue(int v) {
        if(value==null)
            return null;
        if(value==v)
            return this;
        if(value<v)
            return right.findValue(v);
        return left.findValue(v);
    }
}
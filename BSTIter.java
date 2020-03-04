import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class BSTIter {
    public static int[] getSortedArray(int n) {
        int[] output=new int[n];
        for(int i=0;i<n;i++) {
            output[i]=n-i;
        }
        return output;
    }
    public static int[] getRandomArray(int n) {
        int[] output=new int[n];
        HashSet<Integer> inserted=new HashSet<Integer>();
        Random generator=new Random();
        for(int i=0;i<n;i++) {
            Integer random= generator.nextInt();
            if(inserted.contains(random)) {         //already inserted, start over
                i--;
                continue;
            }
            inserted.add(random);
            output[i]=random;
        }
        return output;
    }
    public static ArrayList<Integer> sort(ArrayList<Integer> unsorted) {
        ArrayList<Integer> sorted=new ArrayList<Integer>();                 //array to be returned
        BSTIter bst=new BSTIter();
        for(int i=0;i<unsorted.size();i++) {
            bst.insertIter(unsorted.get(i));
        }
        bst.sortHelper(sorted);                   //adds all elements to a BST and then calls sortHelper() on it
        return sorted;
    }
    private void sortHelper(ArrayList<Integer> sorted) {
        if(root!=null)
            root.sortHelper(sorted);              //calls the sortHelper function on the root node of the tree
    }
    public class Node {
        private int value;
        private Node parent;
        private Node left;
        private Node right;
        private Node(int v,Node p) {
            value=v;
            parent=p;
        }
        private void sortHelper(ArrayList<Integer> sorted) {
            if(left!=null) {                                 //adds elements to array following an in order traversal
                left.sortHelper(sorted);
            }
            sorted.add(value);
            if(right!=null) {
                right.sortHelper(sorted);
            }
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
    }
    private Node root;
    public BSTIter() {}
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
        if(toInsert>curr.value) {                                   //we're at the bottom, insert left or right
            curr.right=new Node(toInsert,curr);
            return;
        }
        curr.left=new Node(toInsert,curr);
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

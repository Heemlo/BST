import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ConstructingTrees();

        System.out.println();
        CompareImplementationsB();

        System.out.println();
        CompareImplementationsC();
    }
    //for 6b
    public static void CompareImplementationsB() {
        int[] Rarray=BSTIter.getRandomArray(10000);
        BSTIter bst=new BSTIter();
        AVL avl=new AVL();
        for(int i=0;i<Rarray.length;i++) {
            avl.insertIter(Rarray[i]);
        }
        System.out.println("avl tree: "+avl.counter);
        for(int i=0;i<Rarray.length;i++) {
            bst.insertIter(Rarray[i]);
        }
        System.out.println("bst tree: "+bst.counter);
    }
    //for 6c
    public static void CompareImplementationsC() {
        int[] Rarray=BSTIter.getSortedArray(10000);
        BSTIter bst=new BSTIter();
        AVL avl=new AVL();
        for(int i=0;i<Rarray.length;i++) {
            avl.insertIter(Rarray[i]);
        }
        System.out.println("avl tree: "+avl.counter);
        for(int i=0;i<Rarray.length;i++) {
            bst.insertIter(Rarray[i]);
        }
        System.out.println("bst tree: "+bst.counter);
    }
    public static void ConstructingTrees() {
        int[] Rarray=BSTIter.getRandomArray(10000);
        BSTIter bst=new BSTIter();
        AVL avl=new AVL();
        for(int i=0;i<Rarray.length;i++) {
            avl.insertIter(Rarray[i]);
        }
        System.out.println("avl tree done");
        for(int i=0;i<Rarray.length;i++) {
            bst.insertIter(Rarray[i]);
        }
        System.out.println("bst done");
    }
}

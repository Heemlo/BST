import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BST testRec=new BST();
        testRec.insertRec(1);
        testRec.insertRec(2);
        testRec.insertRec(3);
        System.out.println(testRec.findMaxRec().getValue());
        System.out.println(testRec.findMinRec().getValue());
        System.out.println(testRec.findMinRec().findNextRec().getValue());
        System.out.println(testRec.findMinRec().findPrevRec());
        testRec.deleteRec(2);
        System.out.println(testRec.findMinRec().findNextRec().getValue());

        System.out.println();
        BSTIter testIter=new BSTIter();
        testIter.insertIter(1);
        testIter.insertIter(2);
        testIter.insertIter(3);
        System.out.println(testIter.findMaxIter().getValue());
        System.out.println(testIter.findMinIter().getValue());
        System.out.println(testIter.findMinIter().findNextIter().getValue());
        System.out.println(testIter.findMinIter().findPrevIter());
        testIter.deleteIter(2);
        System.out.println(testIter.findMinIter().findNextIter().getValue());

        System.out.println();
        ArrayList<Integer> toSort=new ArrayList<Integer>();
        toSort.add(3);
        toSort.add(4);
        toSort.add(1);
        ArrayList<Integer> sorted=BSTIter.sort(toSort);
        System.out.println(sorted);

        int[] array=BSTIter.getRandomArray(5);
        for(int i=0;i<5;i++)
            System.out.println(array[i]);

        System.out.println();
        array=BSTIter.getSortedArray(5);
        for(int i=0;i<5;i++)
            System.out.println(array[i]);
    }
}
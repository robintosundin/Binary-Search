import java.util.ArrayList;

/**
 * A Linear Search implementation of IntSet as opposed to e.g. a Binary Search implementation.
 * @author Chalmers
 */
public class LinSearchIntSet implements IntSet {
    ArrayList<Integer> arr = new ArrayList<Integer>();  // invariant: arr contains no duplicates
    int maxseen = Integer.MIN_VALUE;

    @Override
    public void add(int element) {
        if (element > maxseen) {  // a trick to make Benchmark able to quickly build large instances of this class 
            maxseen = element;
            arr.add(element);
        } else {
            if (!contains(element)) {  // avoid duplicates in array
                arr.add(element);
            }
        }
    }

    @Override
    public boolean contains(int element) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i) == element) return true;
        }
        return false;
    }

    @Override
    public void remove(int element) {
        arr.remove(new Integer(element));
    }

}
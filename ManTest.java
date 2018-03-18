/**
 * Basic manual testing of Binary Search implementation to IntSet.
 */
class ManTest {
    public static void main(String[] args) {
        IntSet set = new BinSearchIntSet();
        set.add(1);
        set.add(3);
        set.add(5);
        set.remove(1);
        System.out.println(set.contains(0));
        System.out.println(set.contains(1));
        System.out.println(set.contains(2));
        System.out.println(set.contains(3));
        System.out.println(set.contains(5));
    }
}
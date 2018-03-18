/**
 * Basic manual testing of Binary Search implementation to GenSet.
 */
class ManTestGen {
    public static void main(String[] args) {
	GenSet<String> kurser = new BinSearchGenSet<>();
        kurser.add("DAT037");
        System.out.println(kurser.contains("DAT037"));
    }
}
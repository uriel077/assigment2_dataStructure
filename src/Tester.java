/**
 * This is a testing framework. Use it extensively to verify that your code is working
 * properly.
 */
public class Tester {

	private static boolean testPassed = true;
	private static int testNum = 0;
	
	/**
	 * This entry function will test all classes created in this assignment.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
	
		// Each function here should test a different class.

		// CharLinkedList
//		testCharLinkedList();

		// SuffixTreeNode
		testSuffixTreeNode();

		// longestRepeatedSuffixTree
//		testLongestRepeatedSuffixTree();
		
		/* TODO - continue writing a function for each class */

		
		// Notifying the user that the code have passed all tests. 
		if (testPassed) {
			System.out.println("All " + testNum + " tests passed!");
		}
	}

	/**
	 * This utility function will count the number of times it was invoked. 
	 * In addition, if a test fails the function will print the error message.  
	 * @param exp The actual test condition
	 * @param msg An error message, will be printed to the screen in case the test fails.
	 */
	private static void test(boolean exp, String msg) {
		testNum++;
		
		if (!exp) {
			testPassed = false;
			System.out.println("Test " + testNum + " failed: "  + msg);
		}
	}

	/**
	 * Checks the CharLinkedList class.
	 */
	private static void testCharLinkedList(){
		CharLinkedList list = new CharLinkedListImpl();
		test(list.size() == 0, "The size of the list should be 0");
		list.add('a');
		test(list.size() == 1, "The size of the list should be 1");
		test(list.firstChar() == 'a', "The first char should be 'a'");
		test(list.last.getChar() == 'a', "The last char of the list should be a");
		list.add('b');
		test(list.size() == 2, "The size of the list should be 2");
		test(list.firstChar() == 'a', "The first char should be 'a'");
		test(list.last.getChar() == 'b', "The last char of the list should be b");
		test(list.toString().equals("ab"), "The list should be ab");
		CharLinkedList many = new CharLinkedListImpl();
		many.append(list);
		test(many.size() == 2, "The size of the list should be 2");
		test(many.firstChar() == 'a', "The first char should be 'a'");
		test(many.last.getChar() == 'b', "The last char of the list should be b");
		test(many.toString().equals("ab"), "The list should be ab");

	}

	/**
	 * Checks the SuffixTreeNode class.
	 */
	private static void testSuffixTreeNode() {
		// test empty root
		SuffixTreeNode node = new SuffixTreeNodeImpl();
		test(node.getTotalWordLength() == 0, "node word length should be 0");
		test(node.getNumOfChildren() == 0, "node num of children should be 0");

		// test search, binary search, shiftChildren and addChild
//		SuffixTreeNode child1 = new SuffixTreeNodeImpl(CharLinkedList.from("abc"), node);
//		SuffixTreeNode child2 = new SuffixTreeNodeImpl(CharLinkedList.from("bcd"), node);
//		SuffixTreeNode child3 = new SuffixTreeNodeImpl(CharLinkedList.from("hello"), node);
//		SuffixTreeNode child4 = new SuffixTreeNodeImpl(CharLinkedList.from("mmmmm"), node);
//		node.setChildren(new SuffixTreeNode[]{child1, child2, child3, child4, null, null, null, null}, 4);

		// binary search
//		test(node.binarySearch('b', 0, 3) == child2, "search for 'b' should find child2");

		// search
//		test(node.search('a') == child1, "search for 'a' should return child1");
		test(node.search('x') == null, "search for 'x' should fail");

		// add child
//		SuffixTreeNode child5 = new SuffixTreeNodeImpl(CharLinkedList.from("dog"), node);
//		node.addChild(child5);
//		test(node.getChildren()[2] == child5, "3rd child should be child5");
	}

	private static void testLongestRepeatedSuffixTree(){
		/*testLongestRepeated("mississippi", "issi");
		testLongestRepeated("abc", "X");
		testLongestRepeated("abbc", "b");

		 */
	}

	private static void testLongestRepeated(String word, String expected){
		//test(new longestRepeatedSuffixTreeImpl(word).getLongestRepeatedSubstring().equals(expected), "Longest repeated substring should be " + expected);
	}

}
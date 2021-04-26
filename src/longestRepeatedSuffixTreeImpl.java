
public class longestRepeatedSuffixTreeImpl extends longestRepeatedSuffixTree {

    public longestRepeatedSuffixTreeImpl(String word) {
        super(word);
    }

    private void traverseInternalNodes(SuffixTreeNode node, int nodeDepth) {
        // Recursive iterations over all current node children,
        // Depth Search for the backtracking utilization.
        for(int i = 0;i < node.getNumOfChildren();i++) {
            traverseInternalNodes(node.children[i], nodeDepth + 1);
        }
        // If there are at least 2 children it means this is an internal node,
        // Internal node means it is a candidate to be the max repeated substring in the SuffixTree.
        if(node.getNumOfChildren() >= 2 && this.maxLength < nodeDepth) {
            this.maxLength = nodeDepth;
            this.substringStartNode = node;
        }
    }

    @Override
    public void createLongestRepeatedSubstring() {
        // Invoke to help private function to enable recursive method.
        this.traverseInternalNodes(this.root, 0);

    }

    @Override
    public String getLongestRepeatedSubstring() {
        // If "traverseInternalNodes" wasn't invoke or it wasn't found any max repeated substring in the SuffixTree,
        // tests represent it as 'X' (for example "abc" expects "X").
        if(this.substringStartNode == null || this.maxLength == 0) {
            return "X";
        }
        String composedStr = new String();
        SuffixTreeNode nodePtr = this.substringStartNode;
        // Iterating all the way upward to the parent and append it to return the exact substring.
        for(int i = 0;i < this.maxLength;i++) {
            composedStr = nodePtr.getChars().firstChar() + composedStr;
            nodePtr = nodePtr.getParent();
        }
        return composedStr;
    }
}

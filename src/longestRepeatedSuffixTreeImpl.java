
public class longestRepeatedSuffixTreeImpl extends longestRepeatedSuffixTree {

    public longestRepeatedSuffixTreeImpl(String word) {
        super(word);
    }

    private void traverseInternalNodes(SuffixTreeNode node, int nodeDepth) {
        // Recursive iterations over all current node children,
        // Depth Search for the backtracking utilization.
        int size=1;
        for(int i = 0;i < node.getNumOfChildren();i++) {
           size=1;
            if(node.chars!=null)
                size=node.chars.size();
            traverseInternalNodes(node.children[i], nodeDepth +size);
        }
        size=1;
        if(node.chars!=null)
            size=node.chars.size();
        nodeDepth += size-1;
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
        SuffixTreeNode nodePtr = this.substringStartNode;
        String composedStr = nodePtr.getChars() + "";
        // Iterating all the way upward to the parent and append it to return the exact substring.
        for(int i = nodePtr.getChars().size(); i <= this.maxLength; i += nodePtr.getChars().size()) {
            nodePtr = nodePtr.getParent();
            if(nodePtr.chars == null)
                break;
            composedStr = nodePtr.getChars() + composedStr;

        }
        return composedStr;
    }
}

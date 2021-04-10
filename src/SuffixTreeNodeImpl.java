import java.time.Clock;

public class SuffixTreeNodeImpl extends SuffixTreeNode {

    public SuffixTreeNodeImpl() {
        super();
    }

    public SuffixTreeNodeImpl(CharLinkedList chars, SuffixTreeNode parent) {
        super(chars, parent);
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public SuffixTreeNode search(char c) {
        return this.binarySearch(c, 0, this.getNumOfChildren() - 1);
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public SuffixTreeNode binarySearch(char target, int left, int right) {
        if(right >= left) {
            int mid = left + (right - left) / 2;
            if(this.children[mid].chars.firstChar() == target)
                return this.children[mid];
            if(this.children[mid].chars.firstChar() > target)
                return binarySearch(target, left, mid - 1);
            return binarySearch(target, mid + 1, right);
        }
        return null;
    }

    /**
     *
     * @param
     * @return
     */
    @Override
    public void shiftChildren(int until) {
        SuffixTreeNode[] new_children = new SuffixTreeNode[SuffixTreeNode.ALPHABET_LENGTH]; // new array to replace the current children
        for(int i = 0;i < this.getNumOfChildren() + 1;i++) {
            int j = i; // at this point the new_children is required to leave the until index cell empty and gap one cell ahead
            if(i >= until) j++;
            new_children[j] = this.children[i]; // copy constructor is needed for copying the cell content
        }
        new_children[until] = null; // setting to null for correctness protection
        this.children = new_children; // reassignment for next usages
    }

    /**
     *
     * @param
     */
    @Override
    public void addChild(SuffixTreeNode node) {
        for(int i = 0;i < this.getNumOfChildren();i++) {
            if(node.chars.firstChar() <= this.children[i].chars.firstChar()) {
                this.shiftChildren(i);
                this.children[i] = node;  // copy constructor is needed for copying the cell content
                this.numOfChildren++;
                this.descendantLeaves = 1;
                i = this.getNumOfChildren();
            }
        }
    }

    /**
     *
     * @param
     * @param
     */
    @Override
    public void addSuffix(char[] word, int from) {
        if(from == word.length)
            return;
        char c = word[from];
        SuffixTreeNode node = this.search(c);
        if(node == null) {
            node = new SuffixTreeNodeImpl(); // need to add more attributes to the node object
            this.addChild(node);
        }
        node.addSuffix(word, from + 1);
    }

    /**
     *
     */
    @Override
    public void compress() {
        int childLen = this.getNumOfChildren();
        if(childLen > 1 || childLen == 0)
            return;
        SuffixTreeNode child = this.children[0]; // new SuffixTreeNode(this.children[0]);
        this.chars.last.setNext(child.chars.first); // new CharLinkedList(child.chars).first); // copy constructor is needed
        for(int i = 0;i < child.getNumOfChildren();i++) {
            this.children[i] = child.children[i]; // new SuffixTreeNode(child.children[i]);
        }
        this.children[0] = null;
    }

    /**
     *
     * @param
     * @param
     * @return
     */
    @Override
    public int numOfOccurrences(char[] subword, int from) {
        return 0;
    }
}

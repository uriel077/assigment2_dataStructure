import java.time.Clock;
import java.util.Arrays;

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
        if(this.getNumOfChildren()==0)
            this.children[0] = node;

        for(int i = 0;i <= this.getNumOfChildren();i++) {
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
            node = new SuffixTreeNodeImpl(CharLinkedList.from(c),this); // need to add more attributes to the node object
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
        if( childLen == 0)
            return;
        if(childLen==1 ){
        if( this.children[0].getNumOfChildren()==0){
            this.getChars().append(this.children[0].getChars());
            this.children[0]=null;
        }
        for(SuffixTreeNode child:this.children){
            if(child!=null)
                child.compress();
        }}
    }

    /**
     * find the
     * @param subword Char array representing string to calculate the number of its occurrences in tree's word
     * @param from wich index to start
     * @return num of occurrences
     */
    @Override
    public int numOfOccurrences(char[] subword, int from) {
        SuffixTreeNode node= find_node(subword,from,this);

        return calculate_leaf(node);
    }
    public int calculate_leaf(SuffixTreeNode node){
        if(node==null)
            return 0;
        if(node.getNumOfChildren()==0)
            return 1;
        int sum=0;
        for(SuffixTreeNode child:node.children){
            sum+= calculate_leaf(child);
        }
        return sum;

    }
    private SuffixTreeNode find_node(char[] subword, int from,SuffixTreeNode node){
        if(node==null)
            return null;

        if(this.children.length==0)
            return null;
        from+=highShareIndex(subword,from,node);
        if (from==subword.length){
            return node;
        }
        return find_node(subword,from,node.search(subword[from]));
    }

    /**
     * find the high index that they share togther whith char
     * @param subword the wanted list
     * @return high index that they share
     */
    private int highShareIndex(char[] subword,int from,SuffixTreeNode node){
        int i=0;
        if (node.chars==null){
            return 0;
        }
        for(char c:node.chars){
            if (subword.length==from+i||node.chars.size()==i)
                break;
            if(subword[from+i]!=c)
                break;
            i++;
        }
        return i;
    }
}

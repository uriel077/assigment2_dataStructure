public class CharLinkedListNodeImpl implements ICharLinkedListNode {
    private char value ;
    private ICharLinkedListNode next=null;

    public CharLinkedListNodeImpl(char value){
        this.value=value;
        this.next=null;
    }

    @Override
    public char getChar() {
        return value;
    }

    @Override
    public ICharLinkedListNode getNext() {
        return next;
    }

    @Override
    public void setNext(ICharLinkedListNode next) {
        this.next=next;
    }
}

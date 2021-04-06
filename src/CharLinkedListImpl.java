public class CharLinkedListImpl extends CharLinkedList{

    public CharLinkedListImpl(){
        super();
    }
    /**
     * add char to the linked list
     * @param c Character to add
     */
    @Override
    public void add(char c) {
        ICharLinkedListNode newAppend=new CharLinkedListNodeImpl(c);
        if(this.size()==0){//if this is the firs object
            this.first =newAppend ;
            this.last = newAppend;
        }
        else {//add to the end the object
            this.last.setNext(newAppend);
            this.last=newAppend;
        }
    }

    @Override
    public char firstChar() {
        return this.first.getChar();
    }

    @Override
    public int size() {
        ICharLinkedListNode temp=this.first;
        int size=0;
        while (temp!=null){
            size++;
            temp=temp.getNext();
        }
        return size;
    }

    @Override
    public void append(CharLinkedList toAppend) {
        for (Character c :toAppend ) {
            this.add(c);
        }
    }
}

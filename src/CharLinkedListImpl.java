public class CharLinkedListImpl extends CharLinkedList{

    public CharLinkedListImpl(){
        super();

    }
    @Override
    public void add(char c) {
        ICharLinkedListNode newAppend=new CharLinkedListNodeImpl(c);
        if(this.size()==0){
            this.first =newAppend ;
            this.last = newAppend;
            return;
        }

        this.last.setNext(newAppend);
        this.last=newAppend;
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

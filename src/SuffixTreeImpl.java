public class SuffixTreeImpl extends SuffixTree{

    public SuffixTreeImpl(String word){
        super(word);

    }
    @Override
    public boolean contains(String subword) {
        return numOfOccurrences(subword)!=0;
    }

    @Override
    public int numOfOccurrences(String subword) {
        return getRoot().numOfOccurrences(subword.toCharArray(),0);
    }
}

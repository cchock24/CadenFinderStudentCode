public class Trie {

    String key;
    Trie[] nodes;
    String value;
    long hash;

    public Trie(Pair p){
        key = p.getKey();
        value = p.getValue();
        nodes = new Trie[2];
        hash = p.getHash();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Trie[] getNodes() {
        return nodes;
    }

    public long getHash() {
        return hash;
    }
}


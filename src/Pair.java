public class Pair {

    String key;
    String value;
    long hash;

    public Pair(String k, String v, long l){
        key = k;
        value = v;
        hash = l;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public long getHash() {
        return hash;
    }
}

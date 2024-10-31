import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 *
 * Completed by: Caden Chock
 **/

public class Finder {
    int radix = 256;
    long p = 999999000001L;
    private static final String INVALID = "INVALID KEY";
    Trie root;

    public Finder() {}

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        ArrayList<Pair> keys = new ArrayList<Pair>();
        // Inspired by Code from rolfl
        for(String line = br.readLine(); line != null; line = br.readLine()){
            String[] elements = line.split(",");
            keys.add(new Pair(elements[keyCol], elements[valCol], hash(elements[keyCol])));
        }
        br.close();
        root = setTrie(keys);
    }

    public String query(String key){
        return searchTrie(root, key);
    }

    public Trie setTrie(ArrayList<Pair> pair){
        Trie root = new Trie(pair.getFirst());
        Trie origRoot = root;
        for(int i = 1; i < pair.size(); i++){
            root = origRoot;
            boolean b = false;
            while(!b){
                if(root.getHash() > pair.get(i).getHash()){
                    if(root.getNodes()[0] == null){
                        root.getNodes()[0] = new Trie(pair.get(i));
                        b = true;
                    }
                    else{
                        root = root.getNodes()[0];
                    }
                }
                else{
                    if(root.getNodes()[1] == null){
                        root.getNodes()[1] = new Trie(pair.get(i));
                        b = true;
                    }
                    else{
                        root = root.getNodes()[1];
                    }
                }
            }

        }
        return origRoot;
    }

    public String searchTrie(Trie root, String key){
        long k = hash(key);
        while(root != null){
            if(root.getHash() == k){
                return root.getValue();
            }
            else if(root.getHash() > k){
                root = root.getNodes()[0];
            }
            else{
                root = root.getNodes()[1];
            }
        }
        return INVALID;
    }

    public long hash(String s){
        long hash = 0;
        for(int i = 0; i < s.length(); i++){
            hash = (radix * hash + s.charAt(i)) % p;
        }
        return hash;
    }
}
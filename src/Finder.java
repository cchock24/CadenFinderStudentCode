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
    private static final String INVALID = "INVALID KEY";
    int size;
    //Number of Keys entered
    int n = 0;
    int tableSize = 20;
    Pair[] pairs;


    public Finder() {}

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        pairs = new Pair[tableSize];
        // Inspired by Code from rolfl
        for(String line = br.readLine(); line != null; line = br.readLine()){
            String[] elements = line.split(",");
            size = elements.length;
            insert(pairs, elements[keyCol], elements[valCol]);
            n++;
            if(n > tableSize / 2){
                pairs = resize(pairs);
            }
        }
        br.close();

    }

    public int hash(String s){
        int hash = 0;
        for(int i = 0; i < s.length(); i++){
            hash = (radix * hash + s.charAt(i)) % tableSize;
        }
        return hash;
    }

    public void insert(Pair[] pairs, String key, String value){
        int hash = hash(key);
        while(pairs[hash] != null){
            hash--;
            if(hash < 0){
                hash = tableSize-1;
            }
        }
        pairs[hash] = new Pair(key, value);
    }

    public Pair[] resize(Pair[] pairs){
        tableSize = tableSize*2;
        Pair[] pairNew = new Pair[tableSize];
        for(int i = 0; i < pairs.length; i++){
            if(pairs[i] != null){
                insert(pairNew, pairs[i].getKey(), pairs[i].getValue());
            }
        }
        return pairNew;
    }

    public String query(String key){
        int hash = hash(key);
        while(pairs[hash] != null){
            if(pairs[hash].getKey().equals(key)){
                return pairs[hash].getValue();
            }
            hash--;
            if(hash < 0){
                hash = tableSize-1;
            }
        }
        return INVALID;
    }



}
package chen;

import java.util.HashMap;

class TrieNode {
	// the trie has value c
    char c;
    // has a hashmap maps the key and value
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    
    boolean isLeaf;
    
    public TrieNode() {}
 
    public TrieNode(char c){
        this.c = c;
    }
}

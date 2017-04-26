package chen;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Tree {
	TreeNode<String> root = new TreeNode<String>("root");
	{
	    TreeNode<String> node0 = root.addChild("node0");
	    TreeNode<String> node1 = root.addChild("node1");
	    TreeNode<String> node2 = root.addChild("node2");
	    {
	        TreeNode<String> node20 = node2.addChild(null);
	        TreeNode<String> node21 = node2.addChild("node21");
	        {
	            TreeNode<String> node210 = node20.addChild("node210");
	        }
	    }
	}

	
	
}

class TreeNode<T> {

    T data;
    TreeNode<T> parent;
    List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<TreeNode<T>>();
    }

    public TreeNode<T> addChild(T child) {
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    // other features ...
}



// TrieNode Class
// (1) char c; (2) HashMap<Character, TrieNode>; (3) boolean hasWord
class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    boolean hasWord;
    
    public TrieNode(){
        
    }
    
    public TrieNode(char c){
        this.c = c;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode cur = root;
        HashMap<Character, TrieNode> curChildren = root.children;
        char[] wordArray = word.toCharArray();
        for(int i = 0; i < wordArray.length; i++){
            char wc = wordArray[i];
            if(curChildren.containsKey(wc)){
                cur = curChildren.get(wc);
            } else {
                TrieNode newNode = new TrieNode(wc);
                curChildren.put(wc, newNode);
                cur = newNode;
            }
            curChildren = cur.children;
            if(i == wordArray.length - 1){
                cur.hasWord= true;
            }
        }
    }
    
    
}
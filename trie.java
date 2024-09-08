//time O(1) for search insert and startswith
//space O(1) 

class Trie {
    class TrieNode {
        boolean isEnd;
        TrieNode[] children;
        public TrieNode(){
            isEnd = false;
            children = new TrieNode[26];
        }
    }
    TrieNode root;
    public Trie() {
        this.root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode();
            }
            curr = curr.children[c - 'a'];
        }
        curr.isEnd = true;
    }
    
    public boolean search(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(curr.children[c - 'a'] != null) {
                curr = curr.children[c - 'a'];
            } else {
                return false;//edge case to remember, return false if char (a in this case) not found. Eg: Trie = hello, word == helloa
            }
        }
        return curr.isEnd;
    }
    
    public boolean startsWith(String word) {
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(curr.children[c - 'a'] != null) {
                curr = curr.children[c - 'a'];
            } else {
                return false;
            }
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

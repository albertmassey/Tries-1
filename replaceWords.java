//time O(nl + ml) where n is the no of words in dictionary and m is the no words in sentence and l is the average lenght of word
//space O(1)

class Solution {
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
    
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();  
        for(String word: dictionary) {
            trie.insert(word);
        }
        StringBuilder sb = new StringBuilder();
        String[] senArr = sentence.split(" ");
        for(String word: senArr) {
            boolean found = false;
            for(int i = 0; i < word.length(); i++) {
                String sub = word.substring(0, i+1);
                if(trie.search(sub)) {
                    sb.append(sub);
                    found = true;
                    break;
                }
            }
            if(!found) {
                sb.append(word);
            }
            sb.append(" ");
        }
        return sb.toString().trim(); 
    }
} 

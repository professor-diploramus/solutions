package org.pdiploramus;

import java.util.ArrayList;
import java.util.List;

/**
 */
public class Trie {

    static class Node {
        char c;
        Node[] children = new Node[26];
        boolean wordend;
        String wordSoFar;

        Node(char c) {
            this.c = c;
        }

        Node() {

        }
    }

    private Node root;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        root = new Node();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        Node p = this.root;
        for (int i = 0; i < word.length(); i++) {
            p = insertChar(word.charAt(i), p);
        }
        p.wordend = true;
    }

    private Node insertChar(char c, Node p) {
        Node n = p.children[c - 'a'];
        if (n == null) {
            Node n1 = new Node(c);
            n1.wordSoFar = (p.wordSoFar != null) ? p.wordSoFar + c : c + "";
            p.children[c - 'a'] = n1;
            return n1;
        } else {
            return n;
        }
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        Node p = prefixExists(word);
        return p != null && p.wordend;
    }

    private Node searchChar(char c, Node p) {
        return p.children[c - 'a'];
    }

    private Node prefixExists(String prefix) {
        Node p = root;
        for (int i = 0; i < prefix.length(); i++) {
            p = searchChar(prefix.charAt(i), p);
            if (p == null) return null;
        }
        return p;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return (prefixExists(prefix) != null);
    }

    public List<String> allWordsStartingWith(String prefix) {
        List<String> ret = new ArrayList<>();
        Node p = prefixExists(prefix);
        if (p == null) return ret;
        allPaths(p, ret);
        return ret;
    }

    private void allPaths(Node p, List<String> l) {
        if (p != null) {
            if (p.wordend) l.add(p.wordSoFar);
            for (Node c : p.children) {
                allPaths(c, l);
            }
        }
    }


}

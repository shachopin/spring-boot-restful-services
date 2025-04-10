package solutions.trie;
import java.util.*;

class TrieNode {
    TrieNode[] next = new TrieNode[26];
    Set<String> words = new HashSet<>();
}
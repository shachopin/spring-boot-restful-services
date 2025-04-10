package solutions.trie;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class AutoCompleteSolution {
    public Set<String> findWords(String prefix, TrieNode cur ) {
        // String[] stringArray = {"apple", "banana", "cherry", "applejuice", "action", "apple"};
        // List<String> listOfWords = Arrays.asList(stringArray);
        // TrieNode cur = buildTrie(listOfWords);
        for (char c : prefix.toCharArray()) {
            if (cur.next[c - 'a'] == null) {
                return new HashSet<>();
            }
            cur = cur.next[c - 'a'];
        }
        return cur.words;
    }

    public void buildTrie(String[] words, TrieNode root) { //数据结构的表示可以是class, 可以只是一个node
        for (String word : words) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.next[ch - 'a'] == null) {
                    node.next[ch - 'a'] = new TrieNode();  //array index是以'a'为基准的自然数
                }
                node.next[ch - 'a'].words.add(word);
                node = node.next[ch - 'a'];
            }
        }
    }
}
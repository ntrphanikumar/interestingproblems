package interesting.dsalgos.ds;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Trie implements java.io.Serializable {
    private final TrieNode root = new TrieNode("");

    public void addWord(String word) {
        root.add(word.toUpperCase());
    }

    public boolean removeWord(String word) {
        return root.remove(word.toUpperCase());
    }

    public List<String> withPrefix(String prefix) {
        return root.withPrefix(prefix.toUpperCase(), 0);
    }

    public void print() {
        System.out.println(root.listAllWords());
    }

    private static class TrieNode implements java.io.Serializable {
        private final Map<Character, TrieNode> children = new TreeMap<>();
        private boolean isCompleteWord = false;
        private final String value;

        private TrieNode(String value) {
            this.value = value;
        }

        private void add(String word) {
            Character firstChar = word.charAt(0);
            TrieNode node = children.get(firstChar);
            if (node == null) {
                node = new TrieNode(value + firstChar);
                children.put(firstChar, node);
            }
            if (word.length() == 1) {
                node.isCompleteWord = true;
            } else {
                node.add(word.substring(1));
            }
        }

        private boolean remove(String word) {
            if (word.isEmpty()) {
                if (isCompleteWord) {
                    isCompleteWord = false;
                    return true;
                }
                return false;
            }
            Character firstChar = word.charAt(0);
            if (!children.containsKey(firstChar)) {
                return false;
            }
            TrieNode charNode = children.get(firstChar);
            boolean isDeleted = charNode.remove(word.length() > 1 ? word.substring(1) : "");
            if (isDeleted && !charNode.isCompleteWord && charNode.children.isEmpty()) {
                children.remove(firstChar);
            }
            return isDeleted;
        }

        private List<String> withPrefix(String prefix, int currentCharIdx) {
            Character firstChar = prefix.charAt(currentCharIdx);
            TrieNode node = children.get(firstChar);
            if (node == null) {
                return Collections.emptyList();
            }
            if (currentCharIdx < prefix.length() - 1) {
                return node.withPrefix(prefix, currentCharIdx + 1);
            }
            return node.listAllWords();
        }

        private List<String> listAllWords() {
            List<String> words = new ArrayList<>();
            listAllWords(words);
            return words;
        }

        private void listAllWords(List<String> words) {
            if (isCompleteWord) {
                words.add(value);
            }
            for (Character c : children.keySet()) {
                children.get(c).listAllWords(words);
            }
        }
    }

    public static void persist(Trie trie, String file) {
        try (java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(new java.io.FileOutputStream(file))) {
            oos.writeObject(trie);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static Trie loadFromDisk(String file) {
        try (java.io.ObjectInputStream ois = new java.io.ObjectInputStream(new java.io.FileInputStream("dictionary.ser"))) {
            return (interesting.dsalgos.ds.Trie) ois.readObject();
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.addWord("phani");
        trie.addWord("Jeetu");
        trie.addWord("phaku");
        trie.addWord("pha");
        trie.print();

        System.out.println(trie.withPrefix("Ph"));

        System.out.println(trie.removeWord("pha"));
        trie.print();
        System.out.println(trie.removeWord("phaku"));
        System.out.println(trie.removeWord("phani"));
        trie.print();
        System.out.println(trie.removeWord("phan"));
        trie.print();
        System.out.println(trie.removeWord("pha"));
        trie.print();

        persist(trie, "dictionary.ser");
        Trie loaded = loadFromDisk("dictionary.ser");
        loaded.print();
    }
}

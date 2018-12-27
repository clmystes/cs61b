import java.util.ArrayDeque;
import java.util.Deque;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque d = new ArrayDeque();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }

    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        for (int i = 0; i < word.length(); i++) {
           if (!d.removeLast().toString().equals(String.valueOf(word.charAt(i)))) {
               return false;
           }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cpt) {
        Deque d = wordToDeque(word);
        for (int i = 0; i < word.length(); i++) {
            if (!cpt.equalChars(word.charAt(i), (char) d.removeLast())) {
                return false;
            }
        }
        return true;
    }
}

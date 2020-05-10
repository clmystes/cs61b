import java.util.ArrayDeque;
import java.util.Deque;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque deque = new ArrayDeque();
        for (char c :
                word.toCharArray()) {
            deque.add(c);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        int i = 0;
        int j = word.length() - 1;
        while (i < j) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }

            i++;
            j--;
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cpt) {
        if (word.length() == 1 || word.length() == 0) {
            return true;
        }

        Deque d = wordToDeque(word);
        for (int i = 0; i < word.length(); i++) {
            if (!cpt.equalChars(word.charAt(i), (char) d.removeLast())) {
                return false;
            }
        }
        return true;
    }
}

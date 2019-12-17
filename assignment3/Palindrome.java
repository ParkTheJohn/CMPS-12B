import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 
import java.util.Arrays;
import java.util.ArrayList;

public class Palindrome {

	static WordDictionary dictionary = new WordDictionary(); 

	
	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}

		return words.toArray(new String[0]);
	}

	// Reverses the contents of a stack from the parameter and returns it as a string.
	public static String stackToReverseString(MyStack stack) {
		/* 
		* TODO 3
		*/
	    String reverse = "";
	    MyStack temp = new MyStack();
	    while (!stack.isEmpty()) {
	        temp.push(stack.pop());
	    }
	    while (!temp.isEmpty()) {
	        Object o = temp.pop();
	        if (!reverse.isEmpty()) {
	            reverse += " ";
	        }
	        reverse += o;
	        stack.push(o);
	    }
		return reverse;
		/* 
		* TODO 3
		*/
	}
	
	// Reverses the text from the parameter and removes any non-alphabet character from it.
	public static String reverseStringAndRemoveNonAlpha(String text) {
		/* 
		* TODO 4
		*/
	    MyStack temp = new MyStack();
	    String reverse = "";
	    for (int i = 0; i < text.length(); i++) {
	        char c = text.charAt(i);
	        if (Character.isAlphabetic(c)) {
	            temp.push(c);
	        }
	    }
	    while (!temp.isEmpty()) {
            Object o = temp.pop();
            reverse += o;
	    }
		return reverse;
		/* 
		* TODO 4
		*/
	}

	// Returns true if the text is a palindrome, false if not.
	public static boolean isPalindrome(String text) {
		/* 
		* TODO 5: Implement "explorePalindrome"
		*/
	    String s1 = "";
	    String s2 = "";
	    text = text.toLowerCase();
	    MyStack stack = new MyStack();
	    MyQueue queue = new MyQueue();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isAlphabetic(c)) {
                stack.push(c);
                queue.enqueue(c);
            }
        }
        while (!stack.isEmpty()) {
            Object o = stack.pop();
            if (!s1.isEmpty()) {
                s1 += " ";
            }
            s1 += o;
        }
	    while (!queue.isEmpty()) {
            Object o = queue.dequeue();
            if (!s2.isEmpty()) {
                s2 += " ";
            }
            s2 += o;
        }
	    if (s1.equals(s2)) {
	        return true;
	    } else {
	        return false;
	    }
		/* 
		* TODO 5: Implement "explorePalindrome"
		*/
	}

	// Lists all possible combinations of the endings of the text from the parameter that
	// would make the string a palindrome.
	public static void explorePalindrome(String text) {
	/* 
	* TODO 6: Implement "explorePalindrome" & helper function
	*/
        text = text.toLowerCase();
	    String decompose = reverseStringAndRemoveNonAlpha(text);
	    MyStack decomposition = new MyStack();
	    decomposeText(text, decompose, 0, decomposition);
	}
	
	// Recursive function that decomposes the text from explorePalindrome into words 
	public static void decomposeText(String originalText, String textToDecompose, int index, MyStack decomposition) {
	    if (index == textToDecompose.length()) {
	        System.out.print(originalText + ": ");
	        System.out.println(stackToReverseString(decomposition));
	    } else {
	        String[] words = getWords(textToDecompose, index);
	        for (int i = 0; i < words.length; i++) {
	            decomposition.push(words[i]);
	            decomposeText(originalText, textToDecompose, words[i].length() + index, decomposition);
	            decomposition.pop();
	        }
	    }
	/* 
	* TODO 6
	*/
	}

	// This function looks at the arguments that are passed 
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {

		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		} else {
			String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = {"A", "ABBA", "oh no an oboe", "salami?", "I'm alas, a salami"};
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);

			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {
				for (int i = 0; i < testPalindromes.length; i++) {

					explorePalindrome(testPalindromes[i]);
				}	
			}
			else {
				System.out.println("unknown mode: " + mode);
			}
		}
	}
}
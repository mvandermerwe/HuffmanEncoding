/**
 * 
 */
package cs2420;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.Test;

/**
 * @author markvandermerwe
 *
 */
public class HuffmanTreeUsingWordsTest {

	@Test
	public void test_compute_most_common_word_symbols() {
		ArrayList<Character> simple_words_file = HuffmanTreeUsingWords.read_file(new File("Resources/simple"));
		Hashtable<String, Node> simple_words_top = HuffmanTreeUsingWords.compute_most_common_word_symbols(simple_words_file, 2);
		
		System.out.println(simple_words_top.toString());
	}

}

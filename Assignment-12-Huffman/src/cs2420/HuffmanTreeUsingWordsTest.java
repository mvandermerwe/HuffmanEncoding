/**
 * 
 */
package cs2420;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import org.junit.Test;

/**
 * @author markvandermerwe
 *
 */
public class HuffmanTreeUsingWordsTest {

	private Node root;

	/**
	 * Create a tree to run tests on.
	 */
	public void setUpTree() {
		List<String> ordered_list_of_symbols = new ArrayList<>();
		ArrayList<Character> buffer = HuffmanTreeUsingWords.read_file(new File("Resources/simple"));

		Hashtable<String, Node> top_words = HuffmanTreeUsingWords.compute_most_common_word_symbols(buffer, 2);
		Hashtable<String, Node> all_symbols = HuffmanTreeUsingWords.compute_remaining_single_character_symbols(buffer,
				top_words, ordered_list_of_symbols);

		root = HuffmanTreeUsingWords.create_tree(all_symbols.values());
	}

	/**
	 * Test computing N most commonly used words with small simple file.
	 */
	@Test
	public void test_compute_most_common_word_symbols_simple() {
		ArrayList<Character> simple_words_file = HuffmanTreeUsingWords.read_file(new File("Resources/simple"));
		Hashtable<String, Node> simple_words_top = HuffmanTreeUsingWords
				.compute_most_common_word_symbols(simple_words_file, 3);

		// System.out.println(simple_words_top.toString());
		assertTrue(simple_words_top.containsKey("sauce"));
		assertTrue(simple_words_top.containsKey("bro"));
		assertTrue(simple_words_top.containsKey("war"));
	}

	/**
	 * Test computing N most commonly used words with the declaration of
	 * independence.
	 */
	@Test
	public void test_compute_most_common_word_symbols_declaration() {
		ArrayList<Character> declaration_words_file = HuffmanTreeUsingWords
				.read_file(new File("Resources/decl_of_ind"));
		Hashtable<String, Node> declaration_words_top = HuffmanTreeUsingWords
				.compute_most_common_word_symbols(declaration_words_file, 2);

		// System.out.println(decleration_words_top.toString());
		assertTrue(declaration_words_top.containsKey("of"));
		assertTrue(declaration_words_top.containsKey("the"));
	}

	/**
	 * Test creating a tree from a simple file.
	 */
	@Test
	public void test_create_tree() {
		List<String> ordered_list_of_symbols = new ArrayList<>();
		ArrayList<Character> buffer = HuffmanTreeUsingWords.read_file(new File("Resources/simple"));

		Hashtable<String, Node> top_words = HuffmanTreeUsingWords.compute_most_common_word_symbols(buffer, 2);
		Hashtable<String, Node> all_symbols = HuffmanTreeUsingWords.compute_remaining_single_character_symbols(buffer,
				top_words, ordered_list_of_symbols);

		Node root = HuffmanTreeUsingWords.create_tree(all_symbols.values());

		assertEquals(null, root.get_parent());
		assertEquals("N_21", root.get_symbol());
	}
	
	

}

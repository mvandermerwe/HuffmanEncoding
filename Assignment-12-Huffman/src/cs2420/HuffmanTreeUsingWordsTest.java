/**
 * 
 */
package cs2420;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Hashtable;
import java.util.List;

import org.junit.Test;

/**
 * Testing of public functions in Huffman Tree. Note all not present are
 * implicitly tested either through this or verifiably through confirmation of
 * successful decompression.
 * 
 * @author Mark Van der Merwe and Anastasia Gonzalez
 */
public class HuffmanTreeUsingWordsTest {

	private Node root;

	/**
	 * Create a tree to run tests on. Tests run on this in test_create_tree.
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
				.compute_most_common_word_symbols(simple_words_file, 2);

		// System.out.println(simple_words_top.toString());
		assertTrue(simple_words_top.containsKey("awesome"));
		assertTrue(simple_words_top.containsKey("bro"));
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
		assertEquals("N_14", root.get_symbol());
	}

	/**
	 * Test grabbing and adding the remaining characters after the original word
	 * into our tree.
	 */
	@Test
	public void test_compute_remaining_single_character_symbols() {
		List<String> ordered_list_of_symbols = new ArrayList<>();
		ArrayList<Character> buffer = HuffmanTreeUsingWords.read_file(new File("Resources/simple"));

		Hashtable<String, Node> top_words = HuffmanTreeUsingWords.compute_most_common_word_symbols(buffer, 2);
		Hashtable<String, Node> all_symbols = HuffmanTreeUsingWords.compute_remaining_single_character_symbols(buffer,
				top_words, ordered_list_of_symbols);

		assertTrue(all_symbols.containsKey("w"));
		assertEquals(2, all_symbols.get("w").get_frequency());

		assertTrue(all_symbols.containsKey("t"));
		assertEquals(1, all_symbols.get("t").get_frequency());

		assertTrue(all_symbols.containsKey("awesome"));
		assertEquals(2, all_symbols.get("awesome").get_frequency());

		// This test makes sure we grab characters out of the words that show up
		// less frequently.
		assertTrue(all_symbols.containsKey("s"));
		assertEquals(3, all_symbols.get("s").get_frequency());
	}

	/**
	 * Test converting from a bitstream to the original symbol.
	 */
	@Test
	public void test_convert_bitstream_to_original_symbols() {
		setUpTree();

		BitSet bitsForWords = new BitSet();
		bitsForWords.set(0);
		bitsForWords.set(1);
		bitsForWords.set(3);
		bitsForWords.set(4);
		ByteBuffer bytesToTest = ByteBuffer.wrap(Bit_Operations.get_bytes(bitsForWords));

		ArrayList<String> words = (ArrayList<String>) HuffmanTreeUsingWords
				.convert_bitstream_to_original_symbols(bytesToTest, root);
		assertEquals(words.get(0), "w");
	}

	/**
	 * Test converting from original symbol to compressed version using our
	 * tree.
	 */
	@Test
	public void test_build_compressed_bit_stream() {
		List<String> ordered_list_of_symbols = new ArrayList<>();
		ArrayList<Character> buffer = HuffmanTreeUsingWords.read_file(new File("Resources/simple"));

		Hashtable<String, Node> top_words = HuffmanTreeUsingWords.compute_most_common_word_symbols(buffer, 2);
		Hashtable<String, Node> all_symbols = HuffmanTreeUsingWords.compute_remaining_single_character_symbols(buffer,
				top_words, ordered_list_of_symbols);

		HuffmanTreeUsingWords.create_tree(all_symbols.values());

		// We'll only write one to save time.
		List<String> a_few_words_from_file = new ArrayList<String>();
		a_few_words_from_file.add("awesome");

		BitSet bitsForWords = new BitSet();
		bitsForWords.set(0);
		try {
			assertArrayEquals(Bit_Operations.get_bytes(bitsForWords),
					HuffmanTreeUsingWords.build_compressed_bit_stream(a_few_words_from_file, all_symbols));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

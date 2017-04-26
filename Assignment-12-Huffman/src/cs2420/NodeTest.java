package cs2420;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test functionality of our Nodes.
 * 
 * @author Anastasia Gonzalez and Mark Van der Merwe
 */
public class NodeTest {

	@Test
	public void testSymbolAndFreq() {
		Node one = new Node("a", 1);

		assertEquals(one.get_symbol(), "a");
		assertEquals(one.get_frequency(), 1);
	}

	@Test
	public void testParentandSymbol() {
		Node one = new Node("a", 1);
		Node two = new Node("b", 2);
		Node three = new Node("c", one, two);

		assertEquals(null, three.get_parent());
		assertEquals("c", three.get_symbol());
	}

	@Test
	public void testLeftChild() {
		Node one = new Node("a", 1);
		Node two = new Node("b", 2);
		Node three = new Node("c", one, two);
		Node four = new Node("d", 3);
		Node five = new Node("e", three, four);
		three.set_parent(five);

		assertEquals(three, three.parents_left());
		assertEquals(null, five.parents_left());
		assertEquals(three, five.get_left_child());
	}

	@Test
	public void testRightChild() {
		Node one = new Node("a", 1);
		Node two = new Node("b", 2);
		Node three = new Node("c", one, two);
		Node four = new Node("d", 3);
		Node five = new Node("e", three, four);
		three.set_parent(five);

		assertEquals(four, five.get_right_child());
	}

	@Test
	public void testLeftMost() {
		Node one = new Node("a", 1);
		Node two = new Node("b", 2);
		Node three = new Node("c", one, two);
		Node four = new Node("d", 3);
		Node five = new Node("e", three, four);
		Node six = new Node("e", 6);

		one.set_parent(three);
		two.set_parent(three);
		three.set_parent(five);
		four.set_parent(five);

		int number = five.compareTo(six);
		assertTrue(number < 0);
	}

	@Test
	public void testLeaf() {
		Node one = new Node("a", 1);
		Node two = new Node("b", 2);
		Node three = new Node("c", one, two);

		assertTrue(one.leaf());
		assertFalse(three.leaf());
	}

}

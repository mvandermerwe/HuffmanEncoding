package cs2420;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class NodeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSymbolAndFreq() {
		Node one = new Node("a", 1);
		
		assertEquals(one.get_symbol(), "a");
		assertEquals(one.get_frequency(), 1);
	}

	@Test
	public void testParentAndChild() {
		Node one = new Node("a", 1);
		Node two = new Node("b", 2);
		Node three = new Node("c", one, two);
		
		System.out.println(three.toString());

	}

}

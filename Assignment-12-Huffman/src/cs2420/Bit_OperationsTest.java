package cs2420;

import static org.junit.Assert.*;

import java.util.BitSet;
import org.junit.Test;

/**
 * Testing to perform on Bit_Operations, making sure we can handle bits
 * correctly.
 * 
 * @author Anastasia Gonzalez and Mark Van der Merwe
 */
public class Bit_OperationsTest {

	/**
	 * Test grabbing bit from byte, making sure we "flip" correctly.
	 */
	@Test
	public void getBit() {
		assertFalse(Bit_Operations.get_bit((byte) 0b0000_0111, 0));
		assertFalse(Bit_Operations.get_bit((byte) 0b0000_0111, 1));
		assertFalse(Bit_Operations.get_bit((byte) 0b0000_0111, 2));
		assertFalse(Bit_Operations.get_bit((byte) 0b0000_0111, 3));
		assertFalse(Bit_Operations.get_bit((byte) 0b0000_0111, 4));
		assertTrue(Bit_Operations.get_bit((byte) 0b0000_0111, 5));
		assertTrue(Bit_Operations.get_bit((byte) 0b0000_0111, 6));
		assertTrue(Bit_Operations.get_bit((byte) 0b0000_0111, 7));
	}

	/**
	 * Make sure we can grab grab the byte array from a bitset with one byte in
	 * it.
	 */
	@Test
	public void bitStreamTest() {
		byte b = (byte) 0b1000_0000;
		BitSet input = BitSet.valueOf(new byte[] { b });
		byte[] output = Bit_Operations.get_bytes(input);
		assertArrayEquals(new byte[] { 0b0000_0001 }, output);
	}

	/**
	 * Make sure we can grab grab the byte array from a bitset with two bytes in
	 * it.
	 */
	@Test
	public void bitStreamTest2() {
		byte b = (byte) 0b1000_0000;
		BitSet input = BitSet.valueOf(new byte[] { b, 0b0011_0101 });
		byte[] output = Bit_Operations.get_bytes(input);
		assertArrayEquals(new byte[] { 0b0000_0001, (byte) 0b1010_1100 }, output);
	}

}

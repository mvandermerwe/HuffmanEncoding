package cs2420;

import static org.junit.Assert.*;

import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;

public class Bit_OperationsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {

	}

	@Test
	public void getBit(){
		assertFalse(Bit_Operations.get_bit((byte)0b0000_0111, 0));
		assertFalse(Bit_Operations.get_bit((byte)0b0000_0111, 1));
		assertFalse(Bit_Operations.get_bit((byte)0b0000_0111, 2));
		assertFalse(Bit_Operations.get_bit((byte)0b0000_0111, 3));
		assertFalse(Bit_Operations.get_bit((byte)0b0000_0111, 4));
		assertTrue(Bit_Operations.get_bit((byte)0b0000_0111, 5));
		assertTrue(Bit_Operations.get_bit((byte)0b0000_0111, 6));
		assertTrue(Bit_Operations.get_bit((byte)0b0000_0111, 7));
	}

	@Test 
	public void bitStreamTest(){
		byte b = (byte) 0b1000_0000;
		BitSet input = BitSet.valueOf(new byte[]{b});
		byte[] output = Bit_Operations.get_bytes(input);
		assertArrayEquals(new byte[]{0b0000_0001}, output);
	}
	
	@Test
	public void bitStreamTest2(){
		byte b = (byte) 0b1000_0000;
		BitSet input = BitSet.valueOf(new byte[]{b, 0b0011_0101});
		byte[] output = Bit_Operations.get_bytes(input);
		assertArrayEquals(new byte[]{0b0000_0001, (byte) 0b1010_1100}, output);
	}

	@Test
	public void testGetBytes() {
		byte b = (byte) 0b1000_0000; //128
		BitSet input = BitSet.valueOf(new byte[]{b});
		byte[] output = Bit_Operations.get_bytes(input);
		assertArrayEquals(new byte[]{0b0000_0001}, output);
	}

}

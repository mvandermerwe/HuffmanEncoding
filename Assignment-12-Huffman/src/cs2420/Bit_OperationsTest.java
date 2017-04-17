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
	public void testGetBytes() {
		byte b = (byte) 0b1000_0000; //128
		BitSet input = BitSet.valueOf(new byte[]{b});
		byte[] output = Bit_Operations.get_bytes(input);
		assertArrayEquals(new byte[]{0b0000_0001}, output);
	}

}

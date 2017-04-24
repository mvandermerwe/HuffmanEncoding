package cs2420;

import java.io.File;
import java.nio.file.Paths;
import java.util.Random;

public class Timing {
	
	private int numberOfWords = 0;
	private long startTime;
	private long endTime;
	private File randomLetters;
	private String stringOfLetters;
	private char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	private long timingRatio;
	private long sizeRatio;

	public void makeAFile(){
		
		Random random = new Random();
		for(int i = 0; i < numberOfWords; i++){
			int letterNumber = random.nextInt(26);
			stringOfLetters = stringOfLetters + letters[letterNumber];
		}
		
		//TODO add string to file
	}
	
	public void timingRatioCalcN(){
		HuffmanTreeUsingWords tree = new HuffmanTreeUsingWords(numberOfWords);
		startTime = System.nanoTime();
		tree.compress_file(randomLetters, randomLetters);
		endTime = System.nanoTime();
		long totalCompressTime = endTime - startTime;
		
		startTime = System.nanoTime();
		tree.decompress_file(Paths.get(randomLetters), randomLetters);
		endTime = System.nanoTime();
		long totalDecompressTime = endTime - startTime;
		
		timingRatio = totalCompressTime / totalDecompressTime;
	}
	
	public void sizeRatioCalcN(){
		HuffmanTreeUsingWords tree = new HuffmanTreeUsingWords(numberOfWords);
		tree.compress_file(randomLetters, randomLetters);
		//TODO get size of file
		long compressedSize;
		
		tree.decompress_file(Paths.get(randomLetters), randomLetters);
		//TODO get size of file
		long decompressedSize;
		
		sizeRatio = compressedSize / decompressedSize;
	}

}

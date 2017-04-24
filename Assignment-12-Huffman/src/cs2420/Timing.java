package cs2420;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

public class Timing {

	private static final int WORD_COUNT_LOWER = 0;
	private static final int WORD_COUNT_UPPER = 1_000;
	private static final int WORD_COUNT_INCREMENT = 50;

	private static final int NUMBER_OF_CHARS_PER_FILE_LOWER = 0;
	private static final int NUMBER_OF_CHARS_PER_FILE_UPPER = 1_000_000;
	private static final int NUMBER_OF_CHARS_PER_FILE_INCREMENT = 50_000;

	private static long startTime;
	private static long endTime;

	private static final String randomLettersFileName = "Resources/random_characters";

	private static final char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	//private static long timingRatio;
	private static long sizeRatio;
	
	public static void main(String[] args) {
		timingRatioCalcN();
	}

	/**
	 * Helper file for creating our random file of data. Format is N random
	 * chars with spaces between.
	 * 
	 * @param the
	 *            number of characters to add to the file.
	 */
	private static void makeAFile(int numOfChars) {
		StringBuilder stringOfLetters = new StringBuilder();

		// Build a random file of size N.
		Random random = new Random();
		for (int character = 0; character < numOfChars; character++) {
			int letterNumber = random.nextInt(26);
			stringOfLetters.append(letters[letterNumber] + " ");
		}

		// Write our random chars to a file.
		sendToFile(stringOfLetters, randomLettersFileName + "_" + numOfChars);
	}

	/**
	 * 
	 */
	public static void timingRatioCalcN() {
		StringBuilder timingForNChars = new StringBuilder();

		// Increment through diff sizes of random files.
		for (int numOfChars = NUMBER_OF_CHARS_PER_FILE_LOWER; numOfChars <= NUMBER_OF_CHARS_PER_FILE_UPPER; numOfChars += NUMBER_OF_CHARS_PER_FILE_INCREMENT) {
			// No words, so we don't care about the words we keep.
			HuffmanTreeUsingWords tree = new HuffmanTreeUsingWords(0);
			
			// Create the file to test on.
			makeAFile(numOfChars);
			
			startTime = System.nanoTime();
			tree.compress_file(new File(randomLettersFileName + "_" + numOfChars), new File(randomLettersFileName + "_" + numOfChars + "_compress"));
			endTime = System.nanoTime();
			long totalCompressTime = endTime - startTime;

			startTime = System.nanoTime();
			tree.decompress_file(Paths.get(randomLettersFileName + "_" + numOfChars + "_compress"), new File(randomLettersFileName + "_" + numOfChars + "_uncompress"));
			endTime = System.nanoTime();
			long totalDecompressTime = endTime - startTime;

			//timingRatio = totalCompressTime / totalDecompressTime;
			// Write our compress and decompress times to a file.
			timingForNChars.append(numOfChars + "," + totalCompressTime + "," + totalDecompressTime + "\n");
		}
		
		sendToFile(timingForNChars, "timingInfo.csv");
	}

	public static void sizeRatioCalcN() {
		HuffmanTreeUsingWords tree = new HuffmanTreeUsingWords(0);
		//tree.compress_file(randomLettersFileName, randomLettersFileName);
		// TODO get size of file
		long compressedSize;

		//tree.decompress_file(Paths.get(randomLettersFileName), randomLettersFileName);
		// TODO get size of file
		long decompressedSize;

		//sizeRatio = compressedSize / decompressedSize;
	}

	/**
	 * Helper method for writing our data to a file.
	 * 
	 * @param fileData
	 *            - the data to be put into the file.
	 * @param filename
	 *            - the name of the file to write to.
	 */
	private static void sendToFile(StringBuilder fileData, String filename) {
		try {
			FileWriter csvWriter = new FileWriter(filename);
			csvWriter.write(fileData.toString());
			csvWriter.close();
		} catch (IOException e) {
			System.out.println("Unable to write to file. Here is the test data, though:");
			System.out.print(fileData.toString());
		}
	}

}

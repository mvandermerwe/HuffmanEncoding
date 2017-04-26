package cs2420;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Timing and compression analysis for our Huffman tree.
 * 
 * @author Mark Van der Merwe and Anastasia Gonzalez
 */
public class Timing {

	// Count of N words to test on.
	private static final int WORD_COUNT_LOWER = 0;
	private static final int WORD_COUNT_UPPER = 1_000;
	private static final int WORD_COUNT_INCREMENT = 50;

	// Count of chars to test on.
	private static final int NUMBER_OF_CHARS_PER_FILE_LOWER = 0;
	private static final int NUMBER_OF_CHARS_PER_FILE_UPPER = 1_000_001;
	private static final int NUMBER_OF_CHARS_PER_FILE_INCREMENT = 50_000;

	// Number of tests to run on each.
	private static final int TESTS = 20;

	private static long startTime;
	private static long endTime;

	private static final String randomLettersFileName = "Resources/random_characters";

	private static final char[] letters = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/**
	 * Run the tests. Uncomment/comment at will!!
	 */
	public static void main(String[] args) {
		// timingRatioCalcN();
		// compressionRatioCalcN();
		timingOnNWordsKept();
		// compressionOnNWordsKept();
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
	 * Perform timing analysis on compression and decompression when changing
	 * the N number of characters in a given file.
	 */
	public static void timingRatioCalcN() {
		StringBuilder timingForNChars = new StringBuilder();

		// Increment through diff sizes of random files.
		for (int numOfChars = NUMBER_OF_CHARS_PER_FILE_LOWER; numOfChars <= NUMBER_OF_CHARS_PER_FILE_UPPER; numOfChars += NUMBER_OF_CHARS_PER_FILE_INCREMENT) {

			long totalCompressTime = 0;
			long totalDecompressTime = 0;

			for (int test = 0; test < TESTS; test++) {
				System.out.println("Starting test " + test + " for " + numOfChars);

				// No words, so we don't care about the words we keep.
				HuffmanTreeUsingWords tree = new HuffmanTreeUsingWords(0);

				// Create the file to test on.
				makeAFile(numOfChars);

				startTime = System.nanoTime();
				tree.compress_file(new File(randomLettersFileName + "_" + numOfChars),
						new File(randomLettersFileName + "_" + numOfChars + "_compress"));
				endTime = System.nanoTime();
				totalCompressTime += (endTime - startTime) / TESTS;

				startTime = System.nanoTime();
				tree.decompress_file(Paths.get(randomLettersFileName + "_" + numOfChars + "_compress"),
						new File(randomLettersFileName + "_" + numOfChars + "_uncompress"));
				endTime = System.nanoTime();
				totalDecompressTime = (endTime - startTime) / TESTS;
			}

			// timingRatio = totalCompressTime / totalDecompressTime;
			// Write our compress and decompress times to a file.
			timingForNChars.append(numOfChars + "," + totalCompressTime + "," + totalDecompressTime + "\n");
		}

		sendToFile(timingForNChars, "timingInfoNChars.csv");
	}

	/**
	 * Perform Compression analysis on compression when changing the N number of
	 * characters in a given file. Compression analysis performed by saving the
	 * compressed file size and the compression ratio, which is determined by
	 * dividing the compressed file size by the original file size.
	 */
	public static void compressionRatioCalcN() {
		StringBuilder compressionForNChars = new StringBuilder();

		// Increment through diff sizes of random files. Note add one to avoid
		// divide by zero.
		for (int numOfChars = NUMBER_OF_CHARS_PER_FILE_LOWER
				+ 1; numOfChars <= NUMBER_OF_CHARS_PER_FILE_UPPER; numOfChars += NUMBER_OF_CHARS_PER_FILE_INCREMENT) {
			double fileCompressionRatio = 0;
			long fileCompressedSize = 0;

			for (int test = 0; test < TESTS; test++) {
				System.out.println("Starting test " + test + " for " + numOfChars);

				// No words, so we don't care about the words we keep.
				HuffmanTreeUsingWords tree = new HuffmanTreeUsingWords(0);

				// Create the file to test on.
				makeAFile(numOfChars);

				tree.compress_file(new File(randomLettersFileName + "_" + numOfChars),
						new File(randomLettersFileName + "_" + numOfChars + "_compress"));

				File compressedFile = new File(randomLettersFileName + "_" + numOfChars + "_compress");
				File originalFile = new File(randomLettersFileName + "_" + numOfChars);

				// Add the compression ratio between the compressed and original
				// file.
				fileCompressionRatio += ((double) compressedFile.length() / (double) originalFile.length())
						/ (double) TESTS;
				fileCompressedSize = compressedFile.length();
			}

			// Write our compress and decompress times to a file.
			compressionForNChars.append(numOfChars + "," + fileCompressionRatio + "," + fileCompressedSize + "\n");
		}

		sendToFile(compressionForNChars, "compressionInfoNChars.csv");
	}

	/**
	 * Timing analysis on keeping N words in the Huffman tree.
	 */
	public static void timingOnNWordsKept() {
		StringBuilder timingNWords = new StringBuilder();

		for (int numOfWords = WORD_COUNT_LOWER; numOfWords <= WORD_COUNT_UPPER; numOfWords += WORD_COUNT_INCREMENT) {
			long timeCompressForNWords = 0;
			long timeDecompressForNWords = 0;

			for (int test = 0; test < TESTS; test++) {
				System.out.println("Starting test " + test + " for " + numOfWords);

				HuffmanTreeUsingWords tree = new HuffmanTreeUsingWords(numOfWords);

				// We will use the tale of two cities as our test file.
				// startTime = System.nanoTime();
				// tree.compress_file(new File("Resources/two_cities"),
				// new File("Resources/timeNWords/two_cities_" + numOfWords));
				// endTime = System.nanoTime();
				// timeCompressForNWords += (endTime - startTime) / TESTS;

				startTime = System.nanoTime();
				tree.decompress_file(Paths.get("Resources/timeNWords/two_cities_" + numOfWords),
						new File("Resources/timeNWords/two_cities_" + numOfWords + "_uncompress"));
				endTime = System.nanoTime();
				timeDecompressForNWords += (endTime - startTime) / TESTS;
			}

			timingNWords.append(numOfWords + "," + timeCompressForNWords + "," + timeDecompressForNWords + "\n");
		}

		sendToFile(timingNWords, "timingInfoNWords.csv");
	}

	/**
	 * Compression ratio testing for keeping N words in the tree. Compression is
	 * represented in the same way as it is for our N chars compression analysis
	 * so see above for details.
	 */
	public static void compressionOnNWordsKept() {
		StringBuilder compressionNWords = new StringBuilder();

		for (int numOfWords = WORD_COUNT_LOWER; numOfWords <= WORD_COUNT_UPPER; numOfWords += WORD_COUNT_INCREMENT) {

			System.out.println("Starting test for " + numOfWords);
			HuffmanTreeUsingWords tree = new HuffmanTreeUsingWords(numOfWords);

			// We will use the tale of two cities as our test file.
			tree.compress_file(new File("Resources/two_cities"),
					new File("Resources/timeNWords/two_cities_" + numOfWords));

			long originalSize = new File("Resources/two_cities").length();
			long compressedSize = new File("Resources/timeNWords/two_cities_" + numOfWords).length();

			double compressionRatio = (double) compressedSize / (double) originalSize;
			compressionNWords.append(numOfWords + "," + compressionRatio + "," + compressedSize + "\n");
		}

		sendToFile(compressionNWords, "compressionInfoNWords.csv");
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

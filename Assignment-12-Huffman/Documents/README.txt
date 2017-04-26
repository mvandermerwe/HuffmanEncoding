1) AUTHOR INFORMATION

 Homework 12 - Huffman Encoding
 My Name: Anastasia Gonzalez
 Partner: Mark Van der Merwe
 Date:    April 24, 2017

2) SUMMARY

 Assignment 12 is a huffman encoding. This takes all the characters in a file and compresses 
 them into a tree. As a result the file size can be reduced allowing for easier storage 
 and decompression.

3) LATE WORK
 
 N/A

4) NOTES TO THE TA

 JUnit - Each test involves multiple tests that ensure the full operation of the huffman code works
 		 correctly. However, each aspect is tested individually to create test overlap to ensure 
 		 confidence that the compressing, decompressing, and trees are implemented correctly.
 
 Timing class - The class contains timing stats for add decompression and compression sort operations.
 			  All the timing is cumulative and requires to be averaged by total items in the file.

5) PLEDGE

	I pledge that the work done here was my own and that I have learned how to write
 this program (such that I could throw it out and restart and finish it in a timely
 manner).  I am not turning in any work that I cannot understand, describe, or
 recreate.  Any sources (e.g., web sites) other than the lecture that I used to
 help write the code are cited in my work.  When working with a partner, I have
 contributed an equal share and understand all the submitted work.  Further, I have
 helped write all the code assigned as pair-programming and reviewed all code that
 was written separately.
 
	-Mark Van der Merwe, Anastasia Gonzalez
	
6) DESIGN DECISIONS

 In our analysis we decided to use the ratio to clearly show how good our decompression algorithm
 was. The ratio shows that whether there are any discrempancies between the file decompresssion 
 and compression.

7) PROBLEMS ENCOUNTERED AND THEIR SOLUTIONS
	
 Our biggest problem was understanding the code and what it did. Another problem we encountered was
 using bytes instead of bits and vice versa. It caused lots of problems when building our trees 
 but with lots of debugging we were able to figure out where the problem was. 
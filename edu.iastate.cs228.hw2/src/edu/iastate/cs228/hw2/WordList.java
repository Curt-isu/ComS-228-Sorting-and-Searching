package edu.iastate.cs228.hw2;

import java.io.*;
import java.util.Scanner;

import java.io.File;

/**
 * A simple list of Strings.
 *
 * @author Gavin Monroe
 */
public class WordList implements Cloneable {
	/**
	 * The array holding all of the elements of the list.
	 */
	private String[] words;

	/**
	 * Constructs and initializes the list to have exactly the same contents as the
	 * given array.
	 *
	 * @param contents
	 *            the array with the contents of the new list
	 * @throws NullPointerException
	 *             if {@code contents} is {@code null}
	 */
	public WordList(String[] list) throws NullPointerException {
		// Check Null
		if (list == null) {
			throw new NullPointerException();
		}
		// Loop
		String[] clone = new String[list.length];
		for (int i = 0; i < list.length; i++) {
			clone[i] = list[i];
		}
		// Return
		words = clone;
	}

	/**
	 * Constructs and initializes the list by reading from the indicated file. The
	 * file is read assuming that each line contains a word. The ordering in the
	 * file is the order that will be used by the list.
	 *
	 * @param filename
	 *            the name of the file to read
	 * @throws NullPointerException
	 *             if {@code filename} is {@code null}
	 * @throws FileNotFoundException
	 *             if the file cannot be found
	 */
	public WordList(String filename) throws NullPointerException, FileNotFoundException {
		// 1. Check Null
		// 2. Init components
		// 3. Get Lines for size of array.
		// 4. Set array Size.
		// 5. Init components for scan.
		// 6. Loop through lines add to array.
		// 7. Sort.
		// 8. Set Array.

		// 1. Check Null
		if (filename == null || filename.length() == 0) {
			throw new NullPointerException();
		}

		// 2. Declare needed for init size of array
		File userFile = new File(filename);
		FileReader fReader = new FileReader(userFile);
		LineNumberReader lReader = new LineNumberReader(fReader);

		// 3. Get number of lines for size of array.
		int lines = 0;
		try {
			while (lReader.readLine() != null) {
				lines++;
			}
		} catch (IOException e) {
			// If goes wrong
		}

		// 5. Read File
		Scanner scans = new Scanner(userFile);
		String[] clone = new String[lines]; // 4.
		int counter = 0;

		// 6.Loop through and add to clone, with position
		while (scans.hasNextLine()) {
			clone[counter] = scans.nextLine();
			counter++;
		}
		words = clone;
	}

	/**
	 * Returns the number of elements in the list.
	 *
	 * @return the number of elements in the list
	 */
	public int length() {
		return words.length;
	}

	/**
	 * Returns the element of the list at the indicated index.
	 *
	 * @param idx
	 *            the index of the element to retrieve
	 * @return the element at the indicated index
	 * @throws IndexOutOfBoundsException
	 *             if {@code idx} is negative or greater than or equal to the length
	 *             of the list
	 */
	public String get(int idx) throws IndexOutOfBoundsException {
		return words[idx];
	}

	/**
	 * Sets the element of the list at the indicated index to the given value.
	 *
	 * @param idx
	 *            the index of the element to set
	 * @param newValue
	 *            the new value of the element
	 * @throws IndexOutOfBoundsException
	 *             if {@code idx} is negative or greater than or equal to the length
	 *             of the list
	 */
	public void set(int idx, String newValue) throws IndexOutOfBoundsException {
		words[idx] = newValue;
	}

	/**
	 * Swaps the indicated elements in the list.
	 *
	 * @param idxA
	 *            the index of one of the elements to swap
	 * @param idxB
	 *            the index of the other element to swap
	 * @throws IndexOutOfBoundsException
	 *             if either of {@code idxA} or {@code idxB} is negative or greater
	 *             than or equal to the length of the list
	 */
	public void swap(int idxA, int idxB) throws IndexOutOfBoundsException {
		String temp = words[idxA];
		words[idxA] = words[idxB];
		words[idxB] = temp;
	}

	/**
	 * Returns the array used by the list to store its elements.
	 *
	 * @return the array used by the list to store its elements
	 */
	public String[] getArray() {
		return words;
	}

	/**
	 * Performs a deep copy of the list.
	 */
	@Override
	public WordList clone() {
		try {
			WordList clone = (WordList) super.clone();
			clone.words = new String[words.length];
			for (int i = 0; i < words.length; ++i) {
				clone.words[i] = words[i];
			}
			return clone;
		} catch (Exception e) {
			return null;
		}
	}
}

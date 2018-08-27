package edu.iastate.cs228.hw2;

import java.io.*;
import java.util.Scanner;

/**
 * A class representing an ordering of characters that can be queried to know
 * the position of a given character.
 *
 * @author Gavin Monroe
 */
public class Alphabet {
	/**
	 * A lookup table containing characters and their positions. Sorted by the
	 * character of each entry.
	 */
	private CharAndPos[] lookup;

	/**
	 * Constructs and initializes the ordering to have exactly the ordering of the
	 * elements in the given array.
	 *
	 * @param ordering
	 *            the array containing the characters, in the ordering desired
	 * @throws NullPointerException
	 *             if {@code ordering} is {@code null}
	 */
	public Alphabet(char[] ordering) throws NullPointerException {

		// 1. Check Null
		// 2. Create Array
		// 3. Loop through & Set Array
		// 4. Sort Array
		// 5. Set lookup

		// Check Null
		if (ordering == null || ordering.length == 0) {
			throw new NullPointerException();
		}

		// Create new array with same size.
		CharAndPos[] clone = new CharAndPos[ordering.length];

		// Loop through array and "copy" over the char to the new clone.
		for (int idx = 0; idx < ordering.length; idx++) {
			clone[idx] = new CharAndPos(ordering[idx], idx);
		}
		// Sort
		sort(clone);

		// Set Alphabet
		lookup = clone;
	}

	/**
	 * Constructs and initializes the ordering by reading from the indicated file.
	 * The file is expected to have a single character on each line, and the
	 * ordering in the file is the order that will be used.
	 *
	 * @param filename
	 *            the name of the file to read
	 * @throws NullPointerException
	 *             if {@code filename} is {@code null}
	 * @throws FileNotFoundException
	 *             if the file cannot be found
	 */
	public Alphabet(String filename) throws NullPointerException, FileNotFoundException {

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
		CharAndPos[] clone = new CharAndPos[lines]; // 4.
		int counter = 0;

		// 6.Loop through and add to clone, with position
		while (scans.hasNextLine()) {
			clone[counter] = new CharAndPos(scans.nextLine().charAt(0), counter);
			counter++;
		}

		// 7.Sort
		sort(clone);
		// 8.Set Array
		lookup = clone;
	}

	/**
	 * Returns true if and only if the given character is present in the ordering.
	 *
	 * @param c
	 *            the character to test
	 * @return true if and only if the given character is present in the ordering
	 */
	public boolean isValid(char c) {

		if (binarySearch(c) >= 0) { // Returns middle

			return true;

		}

		// If returns -1;
		return false;
	}

	/**
	 * Returns the position of the given character in the ordering. Returns a
	 * negative value if the given character is not present in the ordering.
	 *
	 * @param c
	 *            the character of which the position will be determined
	 * @return the position of the given character, or a negative value if the given
	 *         character is not present in the ordering
	 */
	public int getPosition(char c) {
		int pos = binarySearch(c);
		if (pos == -1) { // If nothing is found.
			return -1;
		} else {// Return position.
			return lookup[pos].position;
		}
	}

	/**
	 * Returns the index of the entry containing the given character within the
	 * lookup table {@link #lookup}. Returns a negative value if the given character
	 * does not have an entry in the table.
	 *
	 * @param toFind
	 *            the character for which to search
	 * @return the index of the entry containing the given character, or a negative
	 *         value if the given character does not have an entry in the table
	 */
	private int binarySearch(char toFind) {
		int start = 0;
		int end = lookup.length - 1;
		while (start <= end) {
			int mid = start + (end - start) / 2;
			if (toFind < lookup[mid].character) {
				end = mid - 1;
			} else if (toFind > lookup[mid].character) {
				start = mid + 1;
			} else {
				return mid;
			}

		}
		return -1;
	}

	// Copy of QuickSort Class but modified.
	public void sort(CharAndPos[] toSort) throws NullPointerException {
			quickSortRec(toSort, 0, toSort.length - 1);
	}

	private int partition(CharAndPos[] toSort, int start, int end) {
		// Create a Copy
		CharAndPos[] clone = toSort;
		// Set pivot
		char pivot = clone[end].character;
		int init = (start - 1);
		for (int cntr = start; cntr < end; cntr++) {
			if (clone[cntr].character <= pivot) {
				// Increment
				init++;
				// Swap - Manual
				CharAndPos temp = clone[init];
				clone[init] = clone[cntr];
				clone[cntr] = temp;
			}
		}
		// Swap - Manual
		CharAndPos temp = clone[init + 1];
		clone[init + 1] = clone[end];
		clone[end] = temp;

		// Finally Return
		return init + 1;
	}

	private void quickSortRec(CharAndPos[] toSort, int start, int end) {
		if (start >= end)
			return;
		// Start
		int part = partition(toSort, start, end);
		quickSortRec(toSort, start, part - 1);
		quickSortRec(toSort, part + 1, end);
	}

	/**
	 * A PODT class containing a character and a position. Used as the entry type
	 * within {@link Alphabet#lookup lookup}.
	 */
	/* already completed - NEVER TOUCHED */
	private static class CharAndPos {
		/**
		 * The character of the entry.
		 */
		public char character;

		/**
		 * The position of the entry in the ordering.
		 */
		public int position;

		/**
		 * Constructs and initializes the entry with the given values.
		 *
		 * @param character
		 *            the character of the entry
		 * @param position
		 *            the position of the entry
		 */
		public CharAndPos(char character, int position) {
			this.character = character;
			this.position = position;
		}

		@Override
		public boolean equals(Object obj) {
			if (null == obj || this.getClass() != obj.getClass()) {
				return false;
			}

			CharAndPos o = (CharAndPos) obj;

			return this.character == o.character && this.position == o.position;
		}

		@Override
		public int hashCode() {
			return character ^ position;
		}

		@Override
		public String toString() {
			return "{" + character + ", " + position + "}";
		}
	}
}

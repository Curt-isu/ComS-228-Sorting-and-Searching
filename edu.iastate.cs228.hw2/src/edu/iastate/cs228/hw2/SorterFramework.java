package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

/**
 * An class that compares various methods of sorting.
 *
 * @author Gavin Monroe
 */
public class SorterFramework {
	/**
	 * Loads data necessary to run the sorter statistics output, and runs it. The
	 * only logic within this method should be that necessary to use the given file
	 * names to create the {@link AlphabetComparator}, {@link WordList}, and sorters
	 * to use, and then using them to run the sorter statistics output.
	 *
	 * @param args
	 *            an array expected to contain two arguments: - the name of a file
	 *            containing the ordering to use to compare characters - the name of
	 *            a file containing words containing only characters in the other
	 *            file
	 */
	public static void main(String[] args) {
		//args[0] = Alphabet
		//args[1] = WordsList 
		
		//Null
		if (args[1] == null || args[0] == null) {
			throw new NullPointerException();
		}
		//Run
		try {
			//Create Alphabet
			Alphabet alphabet = new Alphabet(args[0]);
			AlphabetComparator comparator = new AlphabetComparator(alphabet);
			//Create Words
			WordList words = new WordList(args[1]);
			//Create Sorters
			Sorter[] sorters = new Sorter[3];
			InsertionSorter iSort = new InsertionSorter();
			MergeSorter mSort = new MergeSorter();
			QuickSorter qSort = new QuickSorter();
			sorters[0] = iSort;
			sorters[1] = mSort;
			sorters[2] = qSort;
			//Run
			SorterFramework toRun = new SorterFramework(sorters, comparator, words, 1000000);
			toRun.run();
		} catch (FileNotFoundException e) {
		}
	}

	/**
	 * The comparator to use for sorting.
	 */
	private Comparator<String> comparator;

	/**
	 * The words to sort.
	 */
	private WordList words;

	/**
	 * The array of sorters to use for sorting.
	 */
	private Sorter[] sorters;

	/**
	 * The total amount of words expected to be sorted by each sorter.
	 */
	private int totalToSort;

	/**
	 * Constructs and initializes the SorterFramework.
	 *
	 * @param sorters
	 *            the array of sorters to use for sorting
	 * @param comparator
	 *            the comparator to use for sorting
	 * @param words
	 *            the words to sort
	 * @param totalToSort
	 *            the total amount of words expected to be sorted by each sorter
	 * @throws NullPointerException
	 *             if any of {@code sorters}, {@code comparator}, {@code words}, or
	 *             elements of {@code sorters} are {@code null}
	 * @throws IllegalArgumentException
	 *             if {@code totalToSort} is negative
	 */
	public SorterFramework(Sorter[] sorters, Comparator<String> comparator, WordList words, int totalToSort) throws NullPointerException, IllegalArgumentException {
		if (words == null || comparator == null || sorters == null) {
			throw new NullPointerException();
		}
		if (totalToSort < 0) {
			throw new IllegalArgumentException();
		}
		this.sorters = sorters;
		this.words = words;
		this.comparator = comparator;
		this.totalToSort = totalToSort;
	}

	/**
	 * Runs all sorters using
	 * {@link Sorter#sortWithStatistics(WordList, Comparator, int)
	 * sortWithStatistics()}, and then outputs the following information for each
	 * sorter: - the name of the sorter - the length of the word list sorted each
	 * time - the total number of words sorted - the total time used to sort words -
	 * the average time to sort the word list - the number of elements sorted per
	 * second - the total number of comparisons performed
	 */
	public void run() {
		//Loop through all sorts
		for (int i = 0; i < sorters.length; i++) {
			
			//Printed as requested above. In order
			double sortNum = 0;
			sorters[i].sortWithStatistics(words, comparator, totalToSort);
			
			//Print First
			System.out.println("Sorter: " + sorters[i].getName());
			System.out.println("Length of Listed Sorted: " + words.getArray().length);
			System.out.println("Total Sorted Words: " + sorters[i].getTotalWordsSorted());
			
			//Time Prints
			long time = sorters[i].getTotalSortingTime(); //To Print the time corretly
			System.out.println("Total Time: " + time + "ms");
			sortNum = determineLCM(totalToSort,words.getArray().length);
			System.out.println("AVG Time Sort: " + (sorters[i].getTotalSortingTime() / sortNum));
			
			//Elements & Comparsions
			System.out.println("Comparisons Performed: " + sorters[i].getTotalComparisons());
			double EPS = (sorters[i].getTotalWordsSorted() / (time));
			System.out.println("Elements(p/s): " + EPS);

			//
		}
	}
	/**
	 * Returns the required lowest common, so we can use it to find the Avg time sort
	 *  @return
	 *  		Returns Lowest Common D.
	 */
	private double determineLCM(int aNum, int bNum) {
		if (aNum % bNum == 0) {
			return aNum  / bNum;
		} else {
			return aNum / (bNum + 1);
		}
	}

}

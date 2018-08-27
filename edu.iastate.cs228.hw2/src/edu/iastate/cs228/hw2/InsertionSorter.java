package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * InsertSort used for Insert Sort
 *
 * @author Gavin Monroe
 */
public class InsertionSorter extends Sorter {
	/**
	 * Sort with Insert
	 *
	 * @author Gavin Monroe
	 */
	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException {
		//Start at End
		int end = toSort.getArray().length;
		//Get Array and set it to clone
		String[] clone = toSort.getArray();
		//Loop
		for (int start = 1; start < end; ++start) {
			//Save String to temp
			String temp = clone[start];
			//Grab one away from start(so it can be different)
			int init = start - 1;
			//If init is smaller than zero and compare is in range = Loop
			while ((init >= 0) && (comp.compare(clone[init], temp) > 0)) {
				//Set sort.
				clone[init + 1] = clone[init];
				//Increment
				init = init - 1;

			}
			//Set sort.
			clone[init + 1] = temp;
		}

	}
}

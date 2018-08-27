package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * QuickSorter
 *
 * @author Gavin Monroe
 *  
 */
public class QuickSorter extends Sorter {
	/**
	 * To call sort and overall start the rec of quick sort.
	 *
	 * @author Gavin Monroe
	 *  
	 */
	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException {
		quickSortRec(toSort, comp, 0, toSort.getArray().length - 1);
	}
	/**
	 * Sort the given range of items using quick sort. Sort the items accessed through the given 
	 * IndexedSortable over the given range of logical indices. From the perspective of the sort algorithm, 
	 * each index between l (inclusive) and r (exclusive) is an addressable entry.
	 *  If the recursion depth falls below getMaxDepth(int), then switch to HeapSort.
	 *
	 * @author Gavin Monroe
	 *  
	 */
	private int partition(WordList list, Comparator<String> comp, int start, int end) {
		String[] clone = list.getArray();
		String pivot = clone[end];
		int init = (start - 1);
		for (int cntr = start; cntr  < end; cntr++) {
			if (comp.compare(clone[cntr], pivot) <= 0) {
				init++;
				//Swap
				String temp = clone[init];
				clone[init] = clone[cntr];
				clone[cntr] = temp;
			}
		}
		//Swap
		String temp = clone[init + 1];
		clone[init + 1] = clone[end];
		clone[end] = temp;
		//Return
		return init + 1;
	}
	/**
	 * To call sort and overall rec of quick sort.
	 *
	 * @author Gavin Monroe
	 *  
	 */
	private void quickSortRec(WordList list, Comparator<String> comp, int start, int end) {
		if (start >= end) return;
		int part = partition(list, comp, start, end);
		quickSortRec(list, comp, start, part - 1);
		quickSortRec(list, comp, part + 1, end);
	}

}

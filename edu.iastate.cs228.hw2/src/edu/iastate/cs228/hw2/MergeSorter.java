package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * MergeSort
 *
 * @author Gavin Monroe
 */
public class MergeSorter extends Sorter {
	/**
	 * Sort
	 *
	 * @author Gavin Monroe
	 *  
	 */
	@Override
	public void sort(WordList toSort, Comparator<String> comp) throws NullPointerException {
		//Start Rec
		mergeSortRec(toSort, comp, 0, toSort.getArray().length - 1);
	}
	/**
	 * Starts the Rec
	 *
	 * @author Gavin Monroe
	 *  
	 */
	private void mergeSortRec(WordList list, Comparator<String> comp, int start, int end) {
		if (start < end) {
			
			int mid = (start + end) / 2; //Get Middle
			//Do left and Right
			mergeSortRec(list, comp, start, mid);
			mergeSortRec(list, comp, mid + 1, end);
			//Combine
			mergeHelper(comp, list.getArray(), start, mid, end);
		}

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
	private void mergeHelper(Comparator<String> comp, String arr[], int start, int mid, int end) {
		int n1 = mid - start + 1;
		int n2 = end - mid;
		
		String Left[] = new String[n1];
		String Right[] = new String[n2];

		for (int i = 0; i < n1; ++i) {
			Left[i] = arr[start + i];
		}
		for (int j = 0; j < n2; ++j) {
			Right[j] = arr[mid + 1 + j];
		}

		int i = 0;
		int j = 0;

		int k = start;
		while (i < n1 && j < n2) {
			if (comp.compare(Left[i], Right[j]) <= 0) {
				arr[k] = Left[i];
				i++;
			} else {
				arr[k] = Right[j];
				j++;
			}
			k++;
		}
		while (i < n1) {
			arr[k] = Left[i];
			i++;
			k++;
		}
		while (j < n2) {
			arr[k] = Right[j];
			j++;
			k++;
		}
	}
}

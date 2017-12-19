

import java.util.LinkedList;
import java.util.Iterator;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) {
		if (list == null) return;
		
		int index = getInsertIndex(list, value);

		if (index >= 0)
			list.add(index, value);
		else
			list.add(value);
	}
	
	public static int getInsertIndex(LinkedList<Integer> list, int value) {
		int index = -1;
		boolean foundIndex = false;
		Iterator<Integer> itr = list.iterator();
		
		while(!foundIndex && itr.hasNext()) {
			index++;
			if (itr.next() >= value) {
				foundIndex = true;
			}
		}
		
		return foundIndex ? index : -1;
	}
	

	public static void removeMaximumValues(LinkedList<String> list, int N) {
		if (list == null || list.isEmpty() || N < 0) return;
		
		for (int i = 0; i < N; i++) {
			String max = getMaxValue(list);
			while(list.contains(max))
				list.remove(max);
			if (list.isEmpty())
				break;
		}
	}
	
	public static String getMaxValue(LinkedList<String> list) {
		String max = list.getFirst();
		for (String s : list) {
			if (s.compareTo(max) > 0)
				max = s;
		}
		return max;
	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {
		if (one == null || two == null || one.size() < two.size() || two.isEmpty()) return false;
		
		for (int i = 0; i < one.size() - two.size() + 1; i++) {
			if(containsAtPosition(one, two, i)) return true;
		}
		return false;
	}
	
	public static boolean containsAtPosition(LinkedList<Integer> one, LinkedList<Integer> two, int startingPosition) {
		int pos = startingPosition;
		for (int i = 0; i < two.size(); i++) {
			if (one.get(pos) != two.get(i)) return false;
			pos++;
		}
		return true;
	}
}

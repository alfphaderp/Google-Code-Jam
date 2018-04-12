package googlecodejam2018.qualification.troublesort;

/*
 * Trouble Sort
 * 
 * Visible test set - PASS
 * Hidden test set - FAIL
 */

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		for(int i = 1; i < t + 1; i++) {
			int n = in.nextInt();
			int[] v = new int[n];
			for(int j = 0; j < n; j++) {
				v[j] = in.nextInt();
			}
			
			int index = -1;
			v = troubleSort(v);
			for(int j = 0; j < n - 1; j++) {
				if(v[j] > v[j + 1]) {
					index = j;
					break;
				}
			}
			
			if(index == -1) {
				System.out.println("Case #" + i + ": OK");
			}
			else {
				System.out.println("Case #" + i + ": " + index);
			}
		}
		
		in.close();
	}
	
	public static int[] troubleSort(int[] l) {
		boolean done = false;
		while(!done) {
			done = true;
			for(int i = 0; i < l.length - 2; i++) {
				if(l[i] > l[i + 2]) {
					done = false;
					int temp = l[i];
					l[i] = l[i + 2];
					l[i + 2] = temp;
				}
			}
		}
		return l;
	}
}

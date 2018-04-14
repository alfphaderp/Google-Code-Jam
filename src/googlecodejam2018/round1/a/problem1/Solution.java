package googlecodejam2018.round1.a.problem1;

import java.util.Scanner;

public class Solution {
	static Scanner in = new Scanner(System.in);
	static final int T = in.nextInt();
	static int R, C, H, V;
	static int[][] waffle;
	static int total;
	static int hSections, vSections;
	static int hTarget, vTarget;
	static int[] hSlices, vSlices;
	
	public static void main(String[] args) {
		for(int t = 1; t <= T; t++) {
			R = in.nextInt();
			C = in.nextInt();
			H = in.nextInt();
			V = in.nextInt();
			waffle = new int[R][C];
			for(int i = 0; i < R; i++) {
				String line = in.next();
				for(int j = 0; j < C; j++) {
					if(line.charAt(j) == '@') {
						waffle[i][j] = 1;
					}
					else {
						waffle[i][j] = 0;
					}
				}
			}
			
			total = getSum(0, 0, R - 1, C - 1);
			hSections = H + 1;
			vSections = V + 1;
			
			if(total % ((vSections) * (hSections)) != 0) {
				System.out.println("IMPOSSIBLE");
			}
			else {
				hTarget = total / (hSections);
				vTarget = total / (vSections);
				
				hSlices = new int[hSections + 1];
				vSlices = new int[vSections + 1];
				
				for(int i = 0; i < hSections; i++) {
					hSlices[i] = i * ((R + 1) / hSections) - 1;
				}
				for(int i = 1; i < vSections; i++) {
					vSlices[i] = i * ((C + 1)/ vSections) - 1;
				}
				hSlices[0] = 0;
				vSlices[0] = 0;
				hSlices[hSections] = R;
				vSlices[vSections] = C;
				
				int prevAction = 0;
				boolean successful = true;
				for(int i = 1; i < hSections; i++) {
					int sectionSum = getSum(hSlices[i - 1], 0, hSlices[i], C - 1);
					if(sectionSum < hTarget) {
						hSlices[i]++;
						i--;
						if(prevAction == -1) {
							successful = false;
							break;
						}
						prevAction = 1;
						if(i == hSlices.length - 1) {
							successful = false;
						}
					}
					if(sectionSum > hTarget) {
						hSlices[i]--;
						i--;
						if(prevAction == 1) {
							successful = false;
							break;
						}
						if(i == hSlices.length - 1) {
							successful = false;
						}
						prevAction = -1;
					}
				}
				
				if(successful) {
					prevAction = 0;
					for(int i = 1; i < vSections; i++) {
						int sectionSum = getSum(0, vSlices[i - 1], R - 1, vSlices[i]);
						if(sectionSum < vTarget) {
							vSlices[i]++;
							i--;
							if(prevAction == -1) {
								successful = false;
							}
							prevAction = 1;
							if(i == hSlices.length - 1) {
								successful = false;
							}
						}
						if(sectionSum > vTarget) {
							vSlices[i]--;
							i--;
							if(prevAction == 1) {
								successful = false;
							}
							if(i == hSlices.length - 1) {
								successful = false;
							}
							prevAction = -1;
						}
					}
				}
				
				if(successful) {
					for(int i = 1; i < hSections; i++) {
						for(int j = 1; j < vSections; j++) {
							int sectionSum = getSum(hSlices[i - 1], vSlices[j - 1], hSlices[i], vSlices[j]);
							if(sectionSum != total / (vSections * hSections)) {
								successful = false;
							}
						}
					}
					
				}
				
				if(successful) {
					System.out.println("POSSIBLE");
				}
				else {
					System.out.println("IMPOSSIBLE");
				}
			}
		}
		
		in.close();
	}
	
	public static int getSum(int startH, int startV, int endH, int endV) {
		int sum = 0;
		
		for(int i = startH; i <= endH; i++) {
			for(int j = startV; j <= endV; j++) {
				sum += waffle[i][j];
			}
		}
		
		return sum;
	}
}

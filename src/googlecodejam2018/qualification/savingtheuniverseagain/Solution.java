package googlecodejam2018.qualification.savingtheuniverseagain;

/*
 * Saving The Universe Again
 * 
 * Visible test set - PASS
 * Hidden test set - PASS
 */

import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int t = in.nextInt();
		
		for(int i = 1; i < t + 1; i++) {
			int d = in.nextInt();
			String p = in.next();
			int h = 0;
			
			if(checkDamage(p) < d) {
				h = 0;
			}
			else {
				while(checkDamage(p) > d) {
					h++;
					p = performOptimalSwap(p);
					if(p.equals("")) {
						h = -1;
						break;
					}
				}
			}
			
			if(h == -1) {
				System.out.println("Case #" + i + ": IMPOSSIBLE");
			}
			else {
				System.out.println("Case #" + i + ": " + h);
			}
		}
		
		in.close();
	}
	
	public static int checkDamage(String p) {
        int strength = 1;
        int damage = 0;
        for(int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == 'S') {
                damage += strength;
            }
            else {
                strength *= 2;
            }
        }
        return damage;
    }
	
	public static String performOptimalSwap(String p) {
		for(int i = p.length() - 1; i > 0; i--) {
			if(p.charAt(i) == 'S' && p.charAt(i - 1) == 'C') {
				return p.substring(0, i - 1) + "SC" + p.substring(i + 1);
			}
		}
		return "";
	}
}
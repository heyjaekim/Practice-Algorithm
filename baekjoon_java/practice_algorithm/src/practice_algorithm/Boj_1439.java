package practice_algorithm;

import java.util.*;

public class Boj_1439 {
	
	public static String str;
	public static int count0 = 0;
	public static int count1 = 0;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		str = sc.next();
		
		if (str.charAt(0) == '1') {
			count0 += 1;
		} else {
			count1 += 1;
		}
		
		for (int i = 0; i < str.length() - 1; i++) {
			if (str.charAt(i) != str.charAt(i + 1)) {
				if (str.charAt(i + 1) == '1') {
					count0++;
				} else {
					count1++;
				}
			}
		}
		System.out.println(Math.min(count0, count1));
	}
}

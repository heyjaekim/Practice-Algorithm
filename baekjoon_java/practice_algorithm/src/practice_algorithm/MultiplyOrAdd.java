package practice_algorithm;

import java.util.*;

public class MultiplyOrAdd {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		
		int result = str.charAt(0) - '0';
		System.out.println(result);
		
		for(int i = 1; i<str.length(); i++) {
			int num = str.charAt(i) - '0';
			if (num <= 1 || result <= 1) {
				result += num;
			} else {
				result *= num;
			}
		}
		System.out.println(result);
	}
}

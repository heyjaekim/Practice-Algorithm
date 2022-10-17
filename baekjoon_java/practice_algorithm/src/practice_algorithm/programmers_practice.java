package practice_algorithm;

import java.util.*;

public class programmers_practice {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String word = sc.next();
		System.out.println(solution(word));
	}
	
	public static String solution(String s) {
		int center = (int)(s.length() / 2);
		if (s.length() % 2 != 0) {
			return s.substring(center, center+1);
		} else {
			return s.substring(center-1, center+1);
		}
	}
}

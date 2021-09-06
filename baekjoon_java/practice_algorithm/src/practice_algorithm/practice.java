package practice_algorithm;

import java.util.*;

public class practice {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		System.out.println(solution(s));

	}

	private static int solution(String s) {
		int answer = 0;
		for (int i=1; i<=(s.length()/2)+1; i++) {
			int result = getSplitedLength(s, i, 1).length();
			answer = i == 1 ? result : (answer > result ? result : answer);
		}
		return answer;
	}

	private static String getSplitedLength(String s, int n, int repeat) {
		if (s.length() < n) return s;
		
		String result = "";
		String preString = s.substring(0, n);
		String postString = s.substring(n, s.length());
		
		if (!postString.startsWith(preString)) {
			if (repeat == 1) return result += preString + getSplitedLength(postString, n, 1);
			return result += Integer.toString(repeat) + preString + getSplitedLength(postString, n, 1);
		}
		
		return result += getSplitedLength(postString, n, repeat+ 1);
		
		
	}

}

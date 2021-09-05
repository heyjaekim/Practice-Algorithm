package practice_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Kakao_문자열압축 {
	
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
//		String s = sc.next();
//		System.out.println(solution(s));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String s = br.readLine();
		bw.write(String.valueOf(solution(s)));
		bw.flush();
		bw.close();
		br.close();
	}

	public static int solution(String s) {
		int answer = 0;
		
		for (int i=1; i<=(s.length()/2)+1; i++) {
			int result = getSplittedLength(s, i, 1).length();
			answer = i == 1 ? result : (answer > result ? result : answer);
		}
		
		return answer;
	}
	
	public static String getSplittedLength(String s, int n, int repeat) {
		if (s.length() < n) return s;
		
		String result = "";
		String preString = s.substring(0, n);
		String postString = s.substring(n, s.length());
		
		if(!postString.startsWith(preString)) {
			if(repeat == 1) return result += preString + getSplittedLength(postString, n, 1);
			return result += Integer.toString(repeat) + preString + getSplittedLength(postString, n, 1);
		}
		
		return result += getSplittedLength(postString, n, repeat + 1);
	}
}

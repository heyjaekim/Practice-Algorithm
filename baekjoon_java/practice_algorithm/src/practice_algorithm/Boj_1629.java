package practice_algorithm;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_1629 {
	public static long C;
	
	public static void main(String[] args) throws IOException {
//		Scanner sc = new Scanner(System.in);
//		long A = sc.nextLong();
//		long B = sc.nextLong();
//		C = sc.nextLong();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		long A = Long.parseLong(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		System.out.println(pow(A, B));
	}
	
	public static long pow(long A, long exp) {
		// 지수가 1일 경우 A^1이므로 그대로 리턴
		if (exp == 1) {
			return A % C;
		}
		
		// 지수가 절반에 해당하는 A^(exponent / 2) 을 구한다.
		long temp = pow(A, exp/2);
		
		/*
		 * 현재 지수가 홀 수 였다면
		 * A ^ (exponent / 2) * A ^ (exponent / 2) * A 이므로
		 * A 를 한 번 더 곱해줘야 한다.
		 * 
		 * ex) A^9 = A^4 * A^4 * A;
		 */
		if (exp % 2 == 1) {
			return (temp * temp % C) * A % C;
		}
		
		return temp * temp % C;
	}
	
	
}

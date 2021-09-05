package practice_algorithm;

import java.util.Scanner;

public class Boj_5585 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int origin = 1000 - n;
		int[] coinType = {500, 100, 50, 10, 5, 1};
		int cnt = 0;
		
		for (int i = 0; i < 6; i++) {
			int coin = coinType[i];
			cnt += origin / coin;
			origin %= coin;
		}
		
		System.out.println(cnt);
		sc.close();
	}
}

package practice_algorithm;

import java.util.*;

public class Adventurer_Guild {
	
	public static int n;
	public static ArrayList<Integer> arrayList = new ArrayList<>();
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		for (int i = 0; i < n; i++) {
			arrayList.add(sc.nextInt());
		}
		
		Collections.sort(arrayList);
		
		int result = 0;
		int cnt = 0;
		
		for (int i = 0; i < n; i++) {
			cnt += 1;
			if (cnt >= arrayList.get(i)) {
				result += 1;
				cnt = 0;
			}
		}
		System.out.println(result);
		sc.close();
	}
}

package practice_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Boj_1655 {
	
	static int N, input, ans;
	static int minSize, maxSize, dif;
	static PriorityQueue<Integer> minHeap;
	static PriorityQueue<Integer> maxHeap;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1 - o2;
			};
		});
		
		maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		
		minSize = maxSize = 0;
		
		N = Integer.parseInt(br.readLine());
		ans = Integer.parseInt(br.readLine());
		bw.write(String.valueOf(ans) + "\n");
		
		for (int i=2; i<=N; i++) {
			input = Integer.parseInt(br.readLine());
			if (input <= ans) {
				maxHeap.offer(input);
				maxSize++;
				
				dif = maxSize - minSize;
				// 작은값 개수가 1개 이상 많다면 중아값을 바꿈 (짝수개일 경우 작은값이 답이므로)
				
				if (dif >= 1) {
					minHeap.offer(ans);
					minSize++;
					ans = maxHeap.poll();
					maxSize--;
				}
			}
			else {
				minHeap.offer(input);
				minSize++;
				
				dif = minSize - maxSize;
				
				if (dif >= 2) {
					maxHeap.offer(ans);
					maxSize++;
					ans = minHeap.poll();
					minSize--;
				}
			}
			bw.write(String.valueOf(ans)+"\n");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}

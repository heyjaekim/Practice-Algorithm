package practice_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_6549_stack {

	static int[] histogram;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N;
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			
			if(N==0) 
				break;
			
			histogram = new int[N];
			for(int i=0; i<N; i++) {
				histogram[i] = Integer.parseInt(st.nextToken());
			}
			sb.append(getArea(N)).append('\n');
			histogram = null;
		}
		System.out.println(sb);
	}
	
	public static long getArea(int len) {
		
		Stack<Integer> stack = new Stack<>();
		
		long maxArea = 0;
		
		for(int i=0; i<len; i++) {
//			if (!stack.isEmpty())
//				System.out.println(stack.peek());
			while(!stack.isEmpty() && histogram[stack.peek()] >= histogram[i]) {
				int height = histogram[stack.pop()];
//				long width;
//				if(stack.isEmpty())
//					width = i;
//				else
//					width = i - 1 - stack.peek();
				long width = stack.isEmpty() ? i : i - 1 - stack.peek();
				
				maxArea = Math.max(maxArea, height * width);
			}
			stack.push(i);
		}
		
		while(!stack.isEmpty()) {
			int height = histogram[stack.pop()];
			long width = stack.isEmpty() ? len : len - 1 - stack.peek();
			maxArea = Math.max(maxArea, width * height);
		}
		return maxArea;
	}
}

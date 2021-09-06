package practice_algorithm;

import java.util.*;


class Food implements Comparable<Food> {
	private int time;
	private int index;
	
	public Food(int time, int index) {
		this.time = time;
		this.index = index;
	}
	
	public int getTime() {
		return this.time;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	// 시간이 짧은 것이 높은 우선순위를 갖도록 설정
	@Override
	public int compareTo(Food other) {
		return Integer.compare(this.time, other.time);
	}
}

class Kakao_무지의먹방라이브 {

	public static void main(String[] args) {
		int[] food_times = {3,1,2};
		int k = 5;
		System.out.println(solution(food_times, k));
	}
	
	public static int solution(int[] food_times, int k) {
		// 전체 음식을 먹는 시간보다 k가 크거나 같다면 -1;
		long summary = 0;
		for (int i = 0; i < food_times.length; i++) {
			summary += food_times[i];
		}
		if (summary <= k) return -1;
		
		PriorityQueue<Food> pq = new PriorityQueue<>();
		for (int i=0; i < food_times.length; i++) {
			// (음식 시간, 음식 번호) 형태로 우선순위 큐에 삽입
			pq.offer(new Food(food_times[i], i+1));
		}
		
		summary = 0;
		long previous = 0;
		long length = food_times.length;
		
		// summary + (현재의 음식 시간 - 이전 음식 시간) * 음식의 개 와 k 비교
		while (summary + ((pq.peek().getTime() - previous) * length) <= k) {
			int now = pq.poll().getTime();
			summary += (now - previous) * length;
			length -= 1; // 다 먹어본 음식 제외
			previous = now; // 이전 음식 시간 재설정
		}
		
		ArrayList<Food> result = new ArrayList<>();
		while (!pq.isEmpty()) {
			result.add(pq.poll());
		}
		
		// 음식의 번호로 기준을 정렬
		Collections.sort(result, new Comparator<Food>() {
			@Override
			public int compare(Food o1, Food o2) {
				return Integer.compare(o1.getIndex(), o2.getIndex());
			}
		});
		
		return result.get((int) ((k - summary) % length)).getIndex();
	}

}

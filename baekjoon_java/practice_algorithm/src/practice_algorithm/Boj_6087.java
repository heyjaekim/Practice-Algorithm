package practice_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj_6087 {
	static int w, h;
	static char[][] map;
	static int[][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static Node start, end;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new char[h][w];
		visit = new int[h][w];
		
		for(int i=0; i<h; i++) {
			Arrays.fill(visit[i], w*h);
		}
		
		boolean flag = false;
		for(int i=0; i<h; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<w; j++) {
				if(map[i][j] == 'C') {
					if(flag) end = new Node(i, j, 0, 4);
					else {
						start = new Node(i, j, 0, 4);
						flag = true;
					}
				}
			}
		}
		
		int answer = bfs();
		System.out.println(answer);
	}
	
	private static int bfs() {
		Queue<Node> queue = new PriorityQueue<>();
		visit[start.x][start.y] = 0;
		queue.add(start);
		
		Node node;
		while(!queue.isEmpty()) {
			node = queue.poll();
			if(node.x == end.x && node.y == end.y) return node.cost;
			
			for(int d=0; d<4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				
				if(!isValid(nx, ny) || map[nx][ny] == '*') continue;
				
				if(node.dir == d || node.dir == 4) {
					if(visit[nx][ny] >= node.cost) {
						visit[nx][ny] = node.cost;
						queue.add(new Node(nx, ny, node.cost, d));
					}
				} else {
					if(visit[nx][ny] >= node.cost + 1) {
						visit[nx][ny] = node.cost + 1;
						queue.add(new Node(nx, ny, node.cost + 1, d));
					}
				}
				
			}
		}
		return -1;
	}
	
	private static boolean isValid(int nx, int ny) {
		if (nx < 0 || ny < 0 || nx >= h || ny >= w) return false;
		return true;
	}
	
}

class Node implements Comparable<Node>{
	int x;
	int y;
	int cost;
	int dir;
	
	public Node(int x, int y, int cost, int dir) {
		this.x = x;
		this.y = y;
		this.cost = cost;
		this.dir = dir;
	}
	
	@Override
	public int compareTo(Node o) {
		return this.cost - o.cost;
	}
	
	
}

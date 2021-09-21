package practice_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_9376_Ans {

	static int T;
	static int N, M;
	static char[][] map;
	static int[][] digit;
	static int[][] sumDigit;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new char[N+2][M+2];
			sumDigit = new int[N+2][M+2];
			
			for(int i=0; i<N+2; i++) {
				Arrays.fill(map[i], '.');
			}
			
			for(int i=1; i<=N; i++) {
				char c[] = br.readLine().toCharArray();
				for(int j=1; j<=M; j++) {
					map[i][j] = c[j-1];
				}
			}
			
			// 바깥에서 접근했을때 열어야하는 문 개수 계산
			BFS(0, 0);
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j] == '$')
						BFS(i,j);
				}
			}
			
			int Answer = sumDigit[0][0];
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(map[i][j]=='#')
						Answer = Math.min(Answer, sumDigit[i][j]-2);
				}
			}
			System.out.println(Answer);
		}
	}
	
	static class Pair{
		int x;
		int y;
		int w;
		
		public Pair(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}		
	}
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	private static void BFS(int x, int y) {
		digit = new int[N+2][M+2];
		for(int i=0; i<N+2; i++) {
			Arrays.fill(digit[i], Integer.MAX_VALUE);
		}
		
		Queue<Pair> q = new LinkedList<>();
		q.add(new Pair(x, y, 0));
		digit[x][y] = 0;
		
		while(!q.isEmpty()) {
			Pair temp = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = temp.x + dx[d];
				int ny = temp.y + dy[d];
				
				// out of boundary
				if(nx < 0 || ny < 0 || nx >= N+2 || ny >= M+2) continue;
				// wall
				if(map[nx][ny] == '*') continue;
				
				int next = (map[nx][ny]=='#')?1:0;
				next += temp.w;
				
				if(digit[nx][ny] > next) {
					digit[nx][ny] = next;
					q.add(new Pair(nx, ny, next));
				}
			}
		}
		
		for(int i=0; i<N+2; i++) {
			for(int j=0; j<M+2; j++) {
				if(map[i][j] == '*') continue;
				sumDigit[i][j] += digit[i][j];
			}
		}
		
	}
}

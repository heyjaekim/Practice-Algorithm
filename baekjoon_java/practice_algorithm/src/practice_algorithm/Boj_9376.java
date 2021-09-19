package practice_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Boj_9376 {
	static int T, h, w, prisonerIdx;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static int[][] prisonerOne, prisonerTwo, sanggeun;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
		
		T = Integer.parseInt(br.readLine());
		int minimumOpenDoor = 0;
		
		for (int tc=0; tc<T; tc++) {
			st = new StringTokenizer(br.readLine());
			h = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			
			map = new char[h+2][w+2];
			Prisoner[] prisoners = new Prisoner[2];
			prisonerIdx = 0;
			
			String line = null;
			for (int i = 0; i < h; i++) {
                line = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i + 1][j + 1] = line.charAt(j);
                    if (line.charAt(j) == '$') {
                        prisoners[prisonerIdx++] = new Prisoner(i + 1, j + 1);
                    }
                }
            }
			prisonerOne = bfs(map, prisoners[0], h, w);
			prisonerTwo = bfs(map, prisoners[1], h, w);
			sanggeun = bfs(map, new Prisoner(0, 0), h, w);
			
//			System.out.println();
//			printMap(prisonerOne);
//			printMap(prisonerTwo);
//			printMap(sanggeun);
			
			minimumOpenDoor = getMinimumSum(prisonerOne, prisonerTwo, sanggeun, map);
			System.out.println(minimumOpenDoor);
		}
		
	}
	
	private static void printMap(int[][] arr) {
		for (int[] a : arr) {
			System.out.println(Arrays.toString(a));
		}
		System.out.println();
	}
	
	private static int getMinimumSum(int[][] prisonerOne, int[][] prisonerTwo, int[][] sanggeun, char[][] map) {
        int minSum;

        minSum = Integer.MAX_VALUE;

        for (int i = 0; i < prisonerOne.length; i++){
            for (int j = 0; j < prisonerOne[i].length; j++){
                if (map[i][j] == '*')
                    continue;

                int sum = prisonerOne[i][j] + prisonerTwo[i][j] + sanggeun[i][j];
                if (map[i][j] == '#') {
                    sum -= 2;
                }
                if (minSum > sum){
                    minSum = sum;
                }
            }
        }
        return (minSum);
    }
	
	private static int[][]bfs(char[][] map, Prisoner prisoner, int h, int w){
		PriorityQueue<Prisoner> queue = new PriorityQueue<>();
		boolean[][] visited = new boolean[h+2][w+2];
		int[][] doorHistory = new int[h+2][w+2];
		
		queue.add(prisoner);
		visited[prisoner.x][prisoner.y] = true;
		
		while(!queue.isEmpty()) {
			Prisoner temp = queue.poll();
			doorHistory[temp.x][temp.y] = temp.openDoor;
			
			for(int i=0; i<4; i++) {
				int nx = temp.x + dx[i];
				int ny = temp.y + dy[i];
				if(0 <= nx && nx < h+2 && 0 <= ny && ny < w+2 
						&& !visited[nx][ny] && map[nx][ny] != '*') {
					visited[nx][ny] = true;
					queue.add(new Prisoner(nx, ny, map[nx][ny] == '#' ? temp.openDoor+1 : temp.openDoor));
				}
			}
		}
		return doorHistory;
		
	}
	
	public static class Prisoner implements Comparable<Prisoner> {
		int x, y, openDoor;
		
		public Prisoner(int x, int y) {
			this.x = x;
			this.y = y;
			this.openDoor = 0;
		}
		
		public Prisoner(int x, int y, int openDoor) {
			this.x = x;
			this.y = y;
			this.openDoor = openDoor;
		}
		
		@Override
		public int compareTo(Prisoner o) {
			return Integer.compare(this.openDoor, o.openDoor);
		}
	}
}


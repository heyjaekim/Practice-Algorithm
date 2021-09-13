package practice_algorithm;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj_2933 {
	static int R, C, N;
	static char board[][];
	static int[][] clusters;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");

        R = Integer.parseInt(inputs[0]);
        C = Integer.parseInt(inputs[1]);

        board = new char[R][C];

        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
            	board[i][j] = tmp.charAt(j);
            }
        }

        N = Integer.parseInt(br.readLine());
        inputs = br.readLine().split(" ");

        for (int i = 0; i < N; i++) {
            int bar = Integer.parseInt(inputs[i]);

            destructMineral(bar, i%2==0?1:2);

            setCluster();

        }

        // print result
        for (int i = 0; i < R; i++) {
            System.out.println(board[i]);
        }
	}
	
	public static void destructMineral(int height, int direction) {
		if (direction == 1) {
			for (int col = 0; col < C; col++) {
                if(board[R-height][col]=='x'){
                	board[R-height][col]='.';
                    return;
                }
            }
        } else {
            for (int col = C - 1; col >= 0; col--) {
                if(board[R-height][col]=='x'){
                	board[R-height][col]='.';
                    return;
                }
            }
		}
	}
	
	public static void setCluster() {
		clusters = new int[R][C]; // 전부 0으로 초기화 되어있음

        int cluster_num = 1;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(board[i][j]=='x' && clusters[i][j]==0){
                    if(findCluster(i,j, cluster_num)){ // 떠있는 클러스터를 발견하면
                        return;
                    }
                    cluster_num++;
                }
            }
        }
	}
	
	public static boolean findCluster(int x, int y, int cluster_num) {
		int[] mi = {0, 0, -1, 1};
        int[] mj = {1, -1, 0, 0};

        int lowest = -1;

        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> arr = new ArrayList<>();

        q.add(new Point(x, y));
        clusters[x][y] = cluster_num;


        while (!q.isEmpty()) {

        	Point now = q.poll();

            lowest = Math.max(lowest, now.x);

            for (int d = 0; d < 4; d++) {
                int nx = now.x + mi[d];
                int ny = now.y + mj[d];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                if (clusters[nx][ny]==0 && board[nx][ny]=='x') {
                    clusters[nx][ny] = cluster_num;
                    q.add(new Point(nx, ny));
                }
            }

            arr.add(now);

        }
        // 클러스터의 가장 아래가 바닥과 맞닿아있지 않으면 = 공중에 떠있으면!
        // 떨어지는 클러스터는 오직 하나만 있음!
        if(lowest!=R-1){
            moveCluster(arr);
            return true;
        }

        return false;
	}
	
	public static void moveCluster(ArrayList<Point> arr) {
		int move = 1;

        for (Point point : arr) { // 원래꺼 다 지우고
            board[point.x][point.y] = '.';
        }

        outerLoop:
        while(true){

            for (Point point : arr) {

                // 밑으로 한칸 내렸을 때 바닥을 넘어가면
                // or 밑으로 한칸 내렸을 때 다른 클러스터와 겹치면
                // ==>그 전까지만 내릴 수 있음
                if (point.x + move == R || board[point.x + move][point.y] == 'x') {
                    move--;
                    break outerLoop;
                }

            }
            move++;
        }

        for (Point point : arr) { // 새로 업데이트
            board[point.x + move][point.y] = 'x';
        }
	}

}

package practice_algorithm;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj_3197 {
	static int R, C;
    static char[][] board;
    static Point[] swan;

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static Queue<int[]> swanQ;
    static Queue<int[]> waterQ;

    static boolean[][] visit_swan;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();

        board = new char[R][C];
        swan = new Point[2];

        swanQ = new LinkedList<>();
        visit_swan = new boolean[R][C];

        waterQ = new LinkedList<>();

        // 데이터 입력
        int index = 0;
        for (int i = 0; i < R; ++i) {
            String line = sc.next();
            for (int j = 0; j < C; ++j) {
            	board[i][j] = line.charAt(j);
                if (board[i][j] == 'L') {
                	board[i][j] = '.';
                    swan[index++] = new Point(i, j);
                }
                if (board[i][j] == '.') {
                    waterQ.add(new int[] { i, j });
                }
            }
        }
		
		// 출발점이 되는 백조
        swanQ.add(new int[] { swan[0].x, swan[0].y });
        visit_swan[swan[0].x][swan[0].y] = true;

        int day = 0;
        while (true) {
            if (move_swan())
                break;
            melt();
            day++;
        }

        System.out.println(day);
	}
	
	private static boolean move_swan() {
		Queue<int[]> nextQ = new LinkedList<>();
        while (!swanQ.isEmpty()) {
            int[] now = swanQ.poll();

            if (now[0] == swan[1].x && now[1] == swan[1].y) {
                return true;
            }

            for (int k = 0; k < 4; ++k) {
                int nr = now[0] + dr[k];
                int nc = now[1] + dc[k];

                if (!isRange(nr, nc) || visit_swan[nr][nc])
                    continue;
                visit_swan[nr][nc] = true;
                if (board[nr][nc] == '.') {
                    swanQ.add(new int[] { nr, nc });
                }
                // 다음날 얼음이 녹아 백조가 지나 갈 수 있음.
                else if (board[nr][nc] == 'X') {
                    nextQ.add(new int[] { nr, nc });
                }
            }
        }
        // q를 다음날 탐색할 지역이 담긴 nextQ로 바꾼다.
        swanQ = nextQ;
        return false;
	}
	
	private static void melt() {
		// 하루가 지나고 얼음이 녹는다
		int size = waterQ.size();
		for (int i=0; i<size; i++) {
			int[] now = waterQ.poll();
			
			for (int k=0; k<4; k++) {
				int nr = now[0] + dr[k];
				int nc = now[1] + dc[k];
				
				if (isRange(nr, nc) && board[nr][nc] == 'X') {
					board[nr][nc] = '.';
					waterQ.add(new int[] {nr, nc});
				}
			}
		}
	}
	
	private static boolean isRange(int nr, int nc) {
		if (0 <= nr && nr < R && 0 <= nc && nc < C) return true;
		return false;
	}
}

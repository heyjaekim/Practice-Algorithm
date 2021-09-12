package practice_algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Boj_10830 {
	final static int mod = 1000;
	static int N;
	static int[][] origin;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());
		
		origin = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<N; j++) {
				/*
				 * B = 1 일때는 pow 과정에서 바로 A가 반환될 수 있다.
				 * 이 때의 경우 만약 원소가 1000 이상일 경우 pow 메소드에서 모듈러 연산이 
				 * 실행되지 않때문에 오답이 되버린다.
				 * 
				 * 이를 방지하기 위해 초기 행렬에도 1000 으로 나눈 나머지 값을 초기화 해준다.
				 */
				origin[i][j] = Integer.parseInt(st.nextToken()) % mod;
			}
		}
		
		int[][] result = pow(origin, B);
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(result[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
	
	public static int[][] pow(int[][] A, long exp) {
		
		if (exp == 1L) {
			return A;
		}
		
		int[][] ret = pow(A, exp / 2);
		ret = multiply(ret, ret);
		
		if(exp % 2 == 1L) {
			ret = multiply(ret, origin);
		}
		return ret;
	}
	
	public static int[][] multiply(int[][] o1, int[][] o2){
		
		int[][] ret = new int[N][N];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				for(int k=0; k<N; k++) {
					ret[i][j] += o1[i][k] * o2[k][j];
					ret[i][j] %= mod;
				}
			}
		}
		return ret;
	}
}

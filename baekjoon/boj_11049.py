# 행렬 곱셈 순서

import sys
input = sys.stdin.readline

N = int(input())
S = [list(map(int, input().split())) for i in range(N)]
dp = [[0] * N for i in range(N)]

for i in range(1, N):
    for j in range(N-i):
        x = j + i
        dp[j][x] = 2 ** 32
        for k in range(j, x):
            # 행렬의 곱 연산 수 -> r1 * c1(=r2) * c2 -> 행렬[r1 * c2] 생성
            dp[j][x] = min(dp[j][x], dp[j][k] + dp[k + 1][x] + S[j][0] * S[k][1] * S[x][1])

print(dp[0][N-1])
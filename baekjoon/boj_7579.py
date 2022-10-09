n, m = map(int, input().split())
memory = list(map(int, input().split()))
cost = list(map(int, input().split()))

dp = [[0 for i in range(n+1)]]
curCost = -1

while(max(dp[curCost]) < m):
    dp.append([0 for i in range(n+1)])
    curCost += 1

    for i in range(n):
        if cost[i] <= curCost:
            dp[curCost][i+1] = max(dp[curCost - cost[i]][i] + memory[i], dp[curCost][i])
        else:
            dp[curCost][i+1] = dp[curCost][i]

print(curCost)
# 미확인 도착지

from heapq import heappop, heappush
import sys


input = sys.stdin.readline

def dijkstra(start):
    heap = []
    heappush(heap, [0, start])
    dp = [100000000 for _ in range(n+1)]
    dp[start] = 0
    while heap:
        dist, cur = heappop(heap)
        for np, w in s[cur]:
            ndist = dist + w
            if dp[np] > ndist:
                dp[np] = ndist
                heappush(heap, [ndist, np])
    return dp

T = int(input())
for _ in range(T):
    n,m,t = map(int, input().split())
    start,g,h = map(int, input().split())
    s = [[] for _ in range(n+1)]
    de = []
    for i in range(m):
        a,b,d = map(int, input().split())
        s[a].append([b,d])
        s[b].append([a,d])

    for k in range(t):
        de.append(int(input()))

    start_ = dijkstra(start)
    g_ = dijkstra(g)
    h_ = dijkstra(h)
    answer = []

    for l in de:
        if start_[g] + g_[h] + h_[l] == start_[l] or start_[h] + h_[g] + g_[l] == start_[l]:
            answer.append(l)
    answer.sort()
    for f in answer:
        print(f, end=' ')
    print()
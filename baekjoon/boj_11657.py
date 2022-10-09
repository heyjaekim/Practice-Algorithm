# 타임머신

from collections import defaultdict
import sys

n, m = map(int, sys.stdin.readline().split())

INF = float('inf')
g = defaultdict(list)

for _ in range(m):
    a, b, c = map(int, sys.stdin.readline().split())
    g[a].append([b, c])

def bellman_ford(s):
    dist = [INF] * (n+1)
    dist[s] = 0
    for _ in range(n-1):
        for u in range(1, n+1):
            for v, c in g[u]:
                if dist[v] > dist[u] + c:
                    dist[v] = dist[u] + c
    
    for u in range(1, n+1):
        for v, c in g[u]:
            if dist[v] > dist[u] + c:
                return False
    
    return dist

dist = bellman_ford(1)

if dist == False:
    print(-1)
else:
    for i in range(2, n+1):
        print(dist[i] if dist[i] < INF else -1)
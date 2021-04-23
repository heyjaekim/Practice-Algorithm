# 2021 kakao blind recruitment 합승택시요금

from heapq import heappush, heappop

INF = int(1e9)

def dijkstra(src, dst):
    global graph
    size = len(graph)
    table = [INF] * size
    table[src] = 0
    pq = [[0, src]]

    while pq:
        w, x = heappop(pq)
        if table[x] < w:
            continue
        for item in graph[x]:
            nx, ncost = item
            ncost += w
            if ncost < table[nx]:
                table[nx] = ncost
                heappush(pq, [ncost, nx])

    return table[dst]

def solution(n,s,a,b,fares):
    global graph
    graph = [[] for _ in range(n + 1)]
    for fare in fares:
        src, dst, cost = fare
        graph[src].append([dst, cost])
        graph[dst].append([src, cost])

    cost = INF
    for i in range(1, n+1):
        cost = min(cost, dijkstra(s, i) + dijkstra(i, a) + dijkstra(i, b))

    return cost


# n, s, a, b, fares = 6, 4, 6, 2, [[4, 1, 10], [3, 5, 24], [5, 6, 2], [3, 1, 41], [5, 1, 24], [4, 6, 50], [2, 4, 66], [2, 3, 22], [1, 6, 25]]
n, s, a, b, fares = 7, 3, 4, 1, [[5, 7, 9], [4, 6, 4], [3, 6, 1], [3, 2, 3], [2, 1, 6]]
# n, s, a, b, fares = 6, 4, 5, 6, [[2,6,6], [6,3,7], [4,6,7], [6,5,11], [2,5,12], [5,3,20], [2,4,8], [4,3,9]]

print(solution(n,s,a,b,fares))

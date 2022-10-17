import heapq

n = int(input())
stations = [list(map(int, input().split())) for _ in range(n)]
arrive, fuel = map(int, input().split())
stations.append([arrive, 0])
stations.sort()
heap = []
ans = 0
for i in range(len(stations)):
    if fuel - stations[i][0] < 0:
        while heap:
            fuel += -heapq.heappop(heap)
            ans += 1
            if fuel - stations[i][0] >= 0:
                break
    if len(heap) == 0 and fuel - stations[i][0] < 0:
        ans = -1
        break
    else:
        heapq.heappush(heap, -stations[i][1])

print(ans)
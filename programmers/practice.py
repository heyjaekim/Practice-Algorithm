# #1
# def solution(s):
#     answer = s
#     words = {
#         0: "zero", 1:"one", 2:"two",
#         3:"three", 4:"four", 5:"five",
#         6:"six", 7:"seven", 8:"eight", 9:"nine"
#     }
#     for i in range(10):
#         word_len = len(words[i])
#         # print(words[i])
#         if words[i] in answer:
#             answer = answer.replace(words[i], str(i))
#             # print(answer)
#     return int(answer)


# string = "one4seveneight"
# print(solution(string))

#2
# from collections import deque

# dx = [-1, 0, 1, 0]
# dy = [0, -1, 0, 1]

# def bfs(i, j, board):
#     queue = deque([(i, j)])
#     check = [[-1] * 5 for _ in range(5)]
#     check[i][j] = 0
#     while queue:
#         x, y = queue.popleft()
#         for k in range(4):
#             nx, ny = x + dx[k], y + dy[k]
#             # 앉아있는 거리가 파티션으로 막혀 있을 경우는 허용
#             if 0 <= nx < 5 and 0 <= ny < 5:
#                 if board[nx][ny] != 'X' and check[nx][ny] == -1:
#                     check[nx][ny] = check[x][y] + 1
#                     if board[nx][ny] == 'P':
#                         # manhattan_dist = abs(i - nx) + abs(j - ny)
#                         if check[nx][ny] <= 2:
#                             # print("origin", i, j)
#                             # print("here", nx, ny)
#                             return False
#                     queue.append((nx, ny))
#     return True

# def solution(places):
#     answer = []
#     people = []
#     # 맨해튼 거리 |r1 - r2| + |c1 - c2| > 2
#     for place in places:
#         board = [list(row.strip()) for row in place]
#         valid = []
#         for i in range(5):
#             for j in range(5):
#                 if board[i][j] == 'P':
#                     valid.append(bfs(i, j, board))
#         if False in valid:
#             answer.append(0)
#         else:
#             answer.append(1)

#         # print(board)

#         # break

#     return answer


# places = [["POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"], ["POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"], ["PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"], ["OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"], ["PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"]]
# print(solution(places))

# # 3
# # from collections import deque

# def solution(n, k, cmd):
#     answer = ['O'] * n
#     li = []
#     for i in range(n):
#         li.append(i)

#     cur_row = k
#     mem_pos, mem_num = [], []
#     for c in cmd:

#         if c[0] == 'D' or c[0] == 'U':
#             order, num = c.split()
#             num = int(num)
#             if order == 'D':
#                 if cur_row + num > len(li) - 1:
#                     cur_row = len(li) - 1
#                 else:
#                     cur_row = cur_row + num
#                 # cur_row = (cur_row + num) % len(li)
#             else:
#                 if cur_row - num < 0:
#                     cur_row = 0
#                 else:
#                     cur_row = cur_row - num
                
#                 # cur_row = (cur_row - num) % len(li)
#             print(li, "cur_row", cur_row)
#         else:
#             order = c
#             if order == 'C':
#                 mem_pos.append(cur_row)
#                 mem_num.append(li[cur_row])
#                 del li[cur_row]
#                 if cur_row == len(li):
#                     cur_row = len(li) - 1
#                 print(li, "del_row", cur_row)
#             else:
#                 li.insert(mem_pos.pop(), mem_num.pop())
#                 print(li, "insert_row", cur_row)

#     for p in mem_num:
#         answer[p] = 'X'
#     return ''.join(answer)

# n, k, cmd = 8, 2, ["D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"]
# print(solution(n, k, cmd))

# 4
# from collections import deque
import heapq

def solution(n, start, end, roads, traps):
    answer = []
    if start == end:
        return 0
    direct = [[] for _ in range(end + 1)]
    reverse = [[] for _ in range(end + 1)]
    # dp = [[int(1e9)] * (end + 1) for _ in range(end + 1)]
    check = [True] * (end + 1)
    for r in roads:
        s, e, c = r
        direct[s].append((e, c))
        reverse[e].append((s, c))

    queue = [(0, start)]
    if start in traps:
        check[start] = not check[start]
    heapq.heapify(queue)
    while queue:
        cost, cur = heapq.heappop(queue)
        if check[cur]:
            for dst, _cost in direct[cur]:
                if dst == end:
                    return cost + _cost
                elif dst in traps:
                    check[dst] = not check[dst]
                    heapq.heappush(queue, (cost + _cost, dst))
                else:
                    heapq.heappush(queue, (cost + _cost, dst))
        else:
            for dst, _cost in reverse[cur]:
                if dst == end:
                    return cost + _cost
                elif dst in traps:
                    check[dst] = not check[dst]
                    heapq.heappush(queue, (cost + _cost, dst))
                else:
                    heapq.heappush(queue, (cost + _cost, dst))

n, start, end, roads, traps = 4, 1, 4, [[1, 2, 1], [3, 2, 1], [2, 4, 1]], [2, 3]
print(solution(n, start, end, roads, traps))
# 경주로 건설

from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]
dr = ['R', 'D', 'L', 'U']

def calc_dir(cur_dir, nex_dir, cost):
    if (cur_dir == 'R' or cur_dir == 'L') and (nex_dir == 'L' or nex_dir == 'R'):  
        return cost + 100
    if (cur_dir == 'D' or cur_dir == 'U') and (nex_dir == 'D' or nex_dir == 'U'):  
        return cost + 100
    if (cur_dir == 'R' or cur_dir == 'L') and (nex_dir == 'D' or nex_dir == 'U'):  
        return cost + 600
    if (cur_dir == 'D' or cur_dir == 'U') and (nex_dir == 'R' or nex_dir == 'L'):  
        return cost + 600

def bfs(x, y, cost, direct):
    queue = deque([(x, y, cost, direct)])
    check = [[0] * N for _ in range(N)]
    check[x][y] = 1
    while queue:
        x, y, cost, cur_dir = queue.popleft()
        if x == N-1 and y == N-1:
            answer.append(cost)
            continue
        for i in range(4):
            nx, ny, new_cost = x + dx[i], y + dy[i], calc_dir(cur_dir, dr[i], cost)
            if nx < 0 or ny < 0 or nx >= N or ny >= N:
                continue
            if not new_board[nx][ny]:
                if not check[nx][ny] or check[nx][ny] > new_cost:
                    check[nx][ny] = new_cost
                    queue.append((nx, ny, new_cost, dr[i]))
    
def solution(board):
    global answer, new_board, N
    new_board = board
    answer = []
    N = len(board)
    bfs(0, 0, 0, 'R')
    bfs(0, 0, 0, 'D')
    return min(answer)
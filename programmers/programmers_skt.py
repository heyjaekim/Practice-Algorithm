from collections import deque
from copy import deepcopy

def solution(s, word_dict):
    answer = 0 

    n = len(word_dict)
    visited = [[[False] * n for _ in range(n)] for _ in range(n)]

    q = deque()

    for i in range(n):
        q.append((word_dict[i], i, 1))
        for j in range(n):
            visited[i][i][j] = True
    print(q)
    while q:
        targetword, idx, cnt = q.popleft()
        print(targetword, idx, cnt)
        if targetword == s:
            answer += 1
            continue
        if cnt >= n:
            continue

        for i, word in enumerate(word_dict):
            if not visited[idx][cnt][i]:
                for j in range(n):
                    visited[idx][j][cnt] = True
                
                q.append((targetword + word, idx, cnt + 1))
                
    
    return answer


s = "centerminus"
word_dict = ["cent", "center", "term", "terminus", "rm", "min", "minus", "us"]
print(solution(s, word_dict))
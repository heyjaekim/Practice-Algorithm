## x,y : cctv 위치
## direction : 감시 방향 (0:up, 1:right, 2:down, 3:left)
def watch(x, y, direction):
    return_set = set()
    for d in direction:
        new_x = x
        new_y = y
        while(1):
            new_x = new_x+dx[d]
            new_y = new_y+dy[d]
            if not(0<=new_x<N and 0<=new_y<M): break
            if office_array[new_x][new_y]==6: break
            ## set.add(리스트) : 불가능, 에러 발생
            ## set.add(튜플) : 가능
            if office_array[new_x][new_y]==0: return_set.add((new_x,new_y))
    return return_set

def dfs(n, union_set):
    global maxv
    if n == len(cctv_allcase):
        if maxv < len(union_set):
            maxv = len(union_set)
        return

    for each_cctv_case in cctv_allcase[n]:
        dfs(n+1, union_set|each_cctv_case)

if __name__ == '__main__':
    ## cctv_allcase 리스트의 길이 = cctv 총 개수
    ## eg. cctv_allcase의 각 원소 = 각 cctv의 모든 가능한 감시영역에 대한 좌표정보
    ## 예를들어 cctv_allcase[0]에 1번 cctv에 대한 정보가 있다고 한다면
    ## cctv_allcase[0]안에는 4개의 원소가 있을 것이다. 4방향에 대해 감시가 가능하므로.
    ## 감시할 수 있는 방향에 대해서 감시 가능한 영역들을 좌표들로 저장해놓은 것.
    cctv_allcase = []
    
    ## up, right, down, left 순서
    Up, Right, Down, Left = 0, 1, 2, 3
    dx = (-1,0,1,0)
    dy = (0,1,0,-1)
    
    ## 세로N 가로M 크기
    N,M = map(int, input().split()) 
    
    ## 사무실 맵 정보
    office_array = [list(map(int, input().split())) for _ in range(N)]
    
    ## cctv_allcase, empty 업데이트 (empty는 0으로 이루어진 공간을 의미한다.)
    empty = 0
    for i in range(N):
        for j in range(M):
            if office_array[i][j] == 0: empty = empty + 1
            elif office_array[i][j] == 1: ## 1번 cctv
                cctv_allcase.append([watch(i,j,[Up]), watch(i,j,[Right]), watch(i,j,[Down]), watch(i,j,[Left])])
            elif office_array[i][j] == 2: ## 2번 cctv
                cctv_allcase.append([watch(i,j,[Up,Down]), watch(i,j,[Right,Left])])
            elif office_array[i][j] == 3: ## 3번 cctv
                cctv_allcase.append([watch(i,j,[Up,Right]), watch(i,j,[Right,Down]), watch(i,j,[Down,Left]), watch(i,j,[Left,Up])])
            elif office_array[i][j] == 4: ## 4번 cctv
                cctv_allcase.append([watch(i,j,[Up,Right,Down]), watch(i,j,[Right,Down,Left]), watch(i,j,[Down,Left,Up]), watch(i,j,[Left,Up,Right])])
            elif office_array[i][j] == 5: ## 5번 cctv
                cctv_allcase.append([watch(i,j,[Up,Right,Down,Left])])

    ## maxv : 사무실에 존재하는 모든 cctv에 대해서, 감시 가능한 영역의 최대크키
    maxv = 0
    dfs(0,set())
    
    ## 빈 공간의 최소값
    print(empty - maxv)
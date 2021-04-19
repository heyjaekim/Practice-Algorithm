# 도형회전

def check(arr, a, b, c, d):
    # 시작 변환 값 미리 지정
    before_num = arr[a+1][b]
    # 시작 변환 값으로 최솟값 지정
    min_num = arr[a+1][b]

    x, y = a, b
    repeat_num = (c-a+1)*(d-b+1) - (c-a-1)*(d-b-1) # 회전 진행 횟수
    for i in range(repeat_num):
        min_num = min(arr[x][y], min_num)
        before_num, arr[x][y] = arr[x][y], before_num

        if y < d and x == a:
            y += 1
        elif y == d and x < c:
            x += 1
        elif x == c and b < y:
            y -= 1
        else:
            x -= 1

    for i in range(len(arr)):
        print(arr[i])
    print("########################")
    return arr, min_num

def solution(rows, columns, queries):
    arr = [[0] * rows for _ in range(columns)]
    num = 1
    result = []
    min_num = 1e9

    for i in range(rows):
        for j in range(columns):
            arr[i][j] = num
            num += 1

    for i in range(len(queries)):
        arr, min_num = check(arr, queries[i][0]-1, queries[i][1]-1, queries[i][2]-1, queries[i][3]-1)
        result.append(min_num)

    return result

rows, columns, queries = 6,6,[[2,2,5,4],[3,3,6,6],[5,1,6,3]]
print(solution(rows, columns, queries))



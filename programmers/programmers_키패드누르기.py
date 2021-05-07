# 키패드 누르기

def est_dist(n, now_l, now_r, pos, hand):
    dist_l = abs(pos[now_l][0] - pos[n][0]) + abs(pos[now_l][1] - pos[n][1])
    dist_r = abs(pos[now_r][0] - pos[n][0]) + abs(pos[now_r][1] - pos[n][1])
    
    if dist_l == dist_r:
        return "R" if hand == "right" else "L"
    return "R" if dist_r < dist_l else "L"

def solution(numbers, hand):
    result = ''
    
    pos = {1:(0, 0), 2:(0, 1), 3:(0, 2),
            4:(1, 0), 5:(1, 1), 6:(1, 2),
            7:(2, 0), 8:(2, 1), 9:(2, 2),
            '*':(3, 0), 0:(3, 1), '#':(3, 2)}
    
    left_nums, right_nums = set([1, 4, 7]), set([3, 6, 9])
    now_l, now_r = '*', '#'
    for n in numbers:
        if n in left_nums:
            result += "L"
            now_l = n
        elif n in right_nums:
            result += "R"
            now_r = n
        else:
            result += est_dist(n, now_l, now_r, pos, hand)
            if result[-1] == "R":
                now_r = n
            else:
                now_l = n

    return result


numbers = [1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5]
hand = "right"
print(solution(numbers, hand))
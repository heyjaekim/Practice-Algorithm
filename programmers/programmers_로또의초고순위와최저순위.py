def solution(lottos, win_nums):
    rank = [6, 6, 5, 4, 3, 2, 1]
    answer = []

    min_count = 0
    for nums in win_nums:
        if nums in lottos:
            min_count += 1

    zero_count = lottos.count(0)
    max_count = zero_count + min_count

    answer.append(rank[max_count])
    answer.append(rank[min_count])

    return answer
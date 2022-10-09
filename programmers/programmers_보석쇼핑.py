# 보석 쇼핑

from collections import defaultdict

def solution(gems):
    answer = [0, 0]
    candidates = []
    start, end = 0, 0
    gems_len, gems_kind = len(gems), len(set(gems))
    gems_dict = defaultdict(int)
    print(gems_dict)
    while True:
        kind = len(gems_dict)
        print(gems_dict)
        if start == gems_len:
            break
        if kind == gems_kind:
            candidates.append((start, end))
            gems_dict[gems[start]] -= 1
            if gems_dict[gems[start]] == 0:
                del gems_dict[gems[start]]
            start += 1
            continue        
        if end == gems_len:
            break
        if kind != gems_kind:
            gems_dict[gems[end]] += 1
            end += 1
            continue
            
    length = float('inf')
    for s, e in candidates:
        if length > e - s:
            length = e - s
            print(e, s)
            answer[0] = s + 1
            answer[1] = e
    return answer

gems = ["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]
print(solution(gems))
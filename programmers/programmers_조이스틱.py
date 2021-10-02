def solution(name):
    # 상하 조정해서 알파벳 바꾸기
    change = [min(ord(i) - ord('A'), ord('Z') - ord(i) + 1) for i in name]
    idx, answer = 0, 0

    while True:

        answer += change[idx]
        if sum(change) == 0:
            break
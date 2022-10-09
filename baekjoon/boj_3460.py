T = int(input())
for _ in range(T):
    n = bin(int(input()))[2:] # binary function 을 쓰면 결과가 string 으로 나온다는 점, oct 나 dec 도 마찬가지겠지만..
    for idx, val in enumerate(n[::-1]):
        if val == '1':
            print(idx, end = ' ')
total = 0
max = 0
for _ in range(10):
    a, b = map(int, input().split())
    total += b - a
    if max < total:
        max = total
print(max)
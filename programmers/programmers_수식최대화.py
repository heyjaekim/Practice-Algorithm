# 수식 최대화

def calc(priority, n, expression):
    if n == 2:
        return str(eval(expression))
    if priority[n] == '*':
        res = eval('*'.join([calc(priority, n+1, e) for e in expression.split('*')]))
    if priority[n] == '+':
        res = eval('+'.join([calc(priority, n+1, e) for e in expression.split('+')]))
    if priority[n] == '-':
        res = eval('-'.join([calc(priority, n+1, e) for e in expression.split('-')]))
    return str(res)

def solution(expression):
    answer = 0
    priorities = [
        ('*', '-', '+'),
        ('*', '+', '-'),
        ('+', '*', '-'),
        ('+', '-', '*'),
        ('-', '*', '+'),
        ('-', '+', '*')
    ]
    for priority in priorities:
        res = int(calc(priority, 0, expression))
        answer = max(answer, abs(res))

    return answer

expression = "100-200*300-500+20"
print(solution(expression))
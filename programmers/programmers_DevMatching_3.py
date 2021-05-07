# 다단계 칫솔 판매

def solution(enroll, referral, seller, amount):
    answer = []
    _enroll = {}
    result = {}
    result["-"] = 0
    
    for e, r in zip(enroll, referral):
        _enroll[e] = r
        result[e] = 0
        
    for s, a in zip(seller, amount):
        a_amount = a * 100
        result[s] += a_amount
        
        while True:
            a_amount = a_amount // 10
            if a_amount < 1:
                break
            else:
                result[s] -= a_amount
                result[_enroll[s]] += a_amount
                s = _enroll[s]
            
            if s == '-':
                break

    for r in result:
        answer.append(result[r])
    answer = answer[1:]
    return answer

enroll = ["john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"]
referral = ["-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"]
seller = ["young", "john", "tod", "emily", "mary"]
amount = [12, 4, 2, 5, 10]
print(solution(enroll, referral, seller, amount))
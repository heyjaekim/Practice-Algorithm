# 신규아이디 추천

import re

def solution(new_id):
    answer = ''
    answer = new_id.lower()
    answer = re.sub('[^a-z0-9\-\_\.]', '', answer)
    answer = re.sub('\.{2,}', '.', answer)
    answer = re.sub('^\.|\.$', '', answer)
    answer = 'a' if answer == '' else answer
    answer = answer[:15] if len(answer) > 15 else answer
    answer = re.sub('\.$', '', answer)
    while True:
        if len(answer) > 2:
            break
        answer = answer + answer[-1]
    return answer
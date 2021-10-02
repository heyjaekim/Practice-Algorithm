def solution(s):
    dict = {}
    en = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine']
    for i in range(10):
        dict[en[i]] = i
    
    result = ''
    eng = ''
    for i in s:
        if i.isdigit():
            result = result + i
        elif i.isalpha():
            eng = eng + i
            if eng in dict.keys():
                result = result + str(dict[eng])
                eng = ''
    
    return int(result)
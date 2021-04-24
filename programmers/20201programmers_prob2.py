# Naiver prb2


def solve(sentence, keyword, skips):
    now = 0
    sentence = list(sentence)
    size = len(skips)
    keyword_size = len(keyword)
    for i in range(size):
        now += skips[i]
        if now == 0 or skips[i] == 0:
            sentence.insert(now, keyword[i])
            now += 1
        else:
            if i >= keyword_size:
                print(now)
                ki = i % keyword_size
                sentence.insert(now, keyword[ki])
                now += 1
                print(sentence)
            else:
                print(now)
                sentence.insert(now, keyword[i])
                now += 1
                print(sentence)
    
    return ''.join(sentence)

sentence = "i love coding"
keyword = "mo"
skips = [0,0,3,2,4]
print(solve(sentence, keyword, skips))
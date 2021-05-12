# 3
# from collections import deque

def solution(n, k, cmd):
    answer = ['O'] * n
    li = []
    for i in range(n):
        li.append(i)

    cur_row = k
    move_cmd = []
    moves = 0
    del_cmd = []
    del_num = 0
    temp = 0
    for c in cmd:
        if c[0] == 'D' or c[0] == 'U' or c[0] == 'C':
            if c[0] == 'C':
                move_cmd.append(('C'))
                del_cmd.append(temp)
                del_num += 1
            else:
                order, num = c.split()
                num = int(num)
                moves += 1
                move_cmd.append((order, num))
        else:
            del_ptr = del_cmd.pop()
            move_cmd = move_cmd[:del_ptr+1] + move_cmd[del_ptr+1:]
            del_num -= 1
        temp += 1

    print(move_cmd)
    
    for move in move_cmd:
        if move[0] == 'C':
            answer[cur_row] = 'X'
            if cur_row == 0:
                cur_row += 1
            elif cur_row == len(answer) - 1:
                cur_row -= 1
        else:
            order, num = move
            if order == 'D':
                if cur_row + num > len(li) - 1:
                    cur_row = len(li) - 1
                else:
                    cur_row = cur_row + num
                # cur_row = (cur_row + num) % len(li)
            elif order :
                if cur_row - num < 0:
                    cur_row = 0
                else:
                    cur_row = cur_row - num
    
    return ''.join(answer)


    # cur_row = k
    # mem_pos, mem_num = [], []
    # for c in cmd:

    #     if c[0] == 'D' or c[0] == 'U':
    #         order, num = c.split()
    #         num = int(num)
    #         if order == 'D':
    #             if cur_row + num > len(li) - 1:
    #                 cur_row = len(li) - 1
    #             else:
    #                 cur_row = cur_row + num
    #             # cur_row = (cur_row + num) % len(li)
    #         else:
    #             if cur_row - num < 0:
    #                 cur_row = 0
    #             else:
    #                 cur_row = cur_row - num
                
    #             # cur_row = (cur_row - num) % len(li)
    #         # print(li, "cur_row", cur_row)
    #     else:
    #         order = c
    #         if order == 'C':
    #             mem_pos.append(cur_row)
    #             mem_num.append(li[cur_row])
    #             del li[cur_row]
    #             if cur_row == len(li):
    #                 cur_row = len(li) - 1
    #             # print(li, "del_row", cur_row)
    #         else:
    #             if mem_pos[-1] <= cur_row:
    #                 cur_row += 1
    #             li.insert(mem_pos.pop(), mem_num.pop())
    #             # print(li, "insert_row", cur_row)

    # for p in mem_num:
    #     answer[p] = 'X'
    # return ''.join(answer)

n, k, cmd = 8, 2, ["D 2", "C", "U 3", "C", "D 4", "C", "U 2", "Z", "Z", "U 1", "C"]
print(solution(n, k, cmd))
import sys

vowels = ('a', 'e', 'i', 'o', 'u')

def solve(password):
    vowel_cnt = 0
    consec_vow = 0
    consec_con = 0
    prev = ''

    for ch in password:
        if ch in vowels:
            vowel_cnt += 1
            consec_vow += 1
            consec_con = 0
        else:
            consec_con += 1
            consec_vow = 0
        
        if consec_vow == 3 or consec_con == 3:
            return False
        
        if prev == ch and ch not in ('e', 'o'):
            return False
        
        prev = ch

    if vowel_cnt == 0:
        return False
    
    return True


while True:
    password = sys.stdin.readline().rstrip()
    if password == 'end': break
    if solve(password):
        print("<{}> is acceptable.".format(password))
    else:
        print("<{}> is not acceptable.".format(password))
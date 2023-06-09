name = input()

if name[0].isupper():
    print("Error!")
    exit()

token = name.split('_')
ans = ""
# Java -> C++
if len(token) == 1:
    idx = 0
    for i in range(len(token[0])):
        if token[0][i].isupper():
            ans += token[0][idx].lower() +token[0][idx + 1:i] + "_"
            idx = i
    ans += token[0][idx].lower() +token[0][idx + 1:]
# C++ -> Java
else:
    if token[0].islower():
        ans += token[0]
    else:
        print("Error!")
        exit()
    for i in range(1, len(token)):
        if token[i].islower():
            ans += token[i][0].upper() + token[i][1:]
        else:
            print("Error!")
            exit()

print(ans)
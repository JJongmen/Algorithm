def isPelindrome(s):
    if s == s[::-1]:
        return True
    return False

def isAkarakaPelindrome(s):
    if len(s) == 1:
        return True
    k = int(len(s) / 2)
    return isPelindrome(s) and isAkarakaPelindrome(s[:k]) and isAkarakaPelindrome(S[len(S) - k:])


S = input()
if isAkarakaPelindrome(S):
    print("AKARAKA")
else:
    print("IPSELENTI")